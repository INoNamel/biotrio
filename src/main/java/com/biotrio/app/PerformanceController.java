package com.biotrio.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindingResult;
import org.springframework.dao.DataIntegrityViolationException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * Controller for MVC design pattern
 */
@Controller
public class PerformanceController {

    @Autowired
    private PerformanceRepository perfRepo;

    @Autowired
    private HttpSession session;


    @ExceptionHandler({DataIntegrityViolationException.class})
    public String handleMysqlDataTruncation(DataIntegrityViolationException ex) {
        return "redirect:/";
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public String handleIllegalArgumentException(IllegalArgumentException ex) {
        return "redirect:/";
    }

    @ExceptionHandler({NumberFormatException.class})
    public String handleTypeMismatch(NumberFormatException ex) {
        return "redirect:/";
    }

    @ExceptionHandler({DateTimeException.class})
    public String handleDateTimeException(DateTimeException ex) {
        return "redirect:/";
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public String handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        return "redirect:/";
    }


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("titles", perfRepo.findAllTitles(true, null));
        return "index";
    }

    @GetMapping("/search/{like}")
    public String search(@PathVariable(name = "like") String like, Model model) {
        model.addAttribute("titles", perfRepo.findAllTitles(true, like));
        return "index";
    }

    @GetMapping("/watch/{id}/")
    public String today(@PathVariable(name = "id") String id, Model model) {
        model.addAttribute("title", perfRepo.findTitle(Integer.parseInt(id)));
        model.addAttribute("performances", perfRepo.findAllPerformancesFor(id, null));
        model.addAttribute("dates", perfRepo.findPerformanceDates(Integer.parseInt(id)));
        model.addAttribute("today", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return "all-dates";
    }

    @GetMapping("/watch/{id}/{date}")
    public String onDate(@PathVariable(name = "id") String id, @PathVariable(name = "date") String date, Model model) {
        model.addAttribute("title", perfRepo.findTitle(Integer.parseInt(id)));
        model.addAttribute("performances", perfRepo.findAllPerformancesFor(id, date));
        model.addAttribute("dates", perfRepo.findPerformanceDates(Integer.parseInt(id)));
        model.addAttribute("today", LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        model.addAttribute("when", date);
        return "all-dates";
    }

    @GetMapping("/watch/{title}/{date}/reserve/{id}")
    public String book(@PathVariable(name = "id") String id, Model model) {

        model.addAttribute("bookingForm", new Booking());
        model.addAttribute("performance", perfRepo.occupySeats(perfRepo.findPerformance(Integer.parseInt(id))));
        return "booking";
    }

    @PostMapping("/watch/{title}/{date}/reserve/{id}")
    public String saveBooking(@ModelAttribute("bookingForm") Booking booking, @PathVariable(name = "title") String title, @PathVariable(name = "date") String date, @PathVariable(name = "id") String id, RedirectAttributes ra) {

        booking.setPerformance(perfRepo.occupySeats(perfRepo.findPerformance(Integer.parseInt(id))));
        ra.addFlashAttribute("msg", perfRepo.validateReservation(booking));
        return "redirect:/watch/"+title+"/"+date+"/reserve/"+id;
    }

    @GetMapping("/logout")
    public String logout() {
        session.removeAttribute("logged");
        return "redirect:/admin";
    }

    @GetMapping("/admin")
    public String login(Login login, Model model) {
        model.addAttribute("loginForm", login);

        return "login";
    }

    @PostMapping("/admin")
    public String login(@ModelAttribute Login login) {
        if(perfRepo.verifyPass(login.getPassword())) {
            session.setAttribute("logged", true);
            return "redirect:/admin/titles";
        } else {
            return "redirect:/admin";
        }
    }

    @GetMapping("/admin/{directory}")
    public String navigateTo(@PathVariable(name = "directory") String directory, Model model){
        if(session.getAttribute("logged") != null && session.getAttribute("logged").equals(true)) {
            switch (directory) {
            case ("titles"):
                model.addAttribute(directory, perfRepo.findAllTitles(false, null));
                return "admin/show-titles";
            case ("performances"):
                model.addAttribute(directory, perfRepo.findAllPerformances());
                return "admin/show-performances";
            case ("theaters"):
                model.addAttribute(directory, perfRepo.findAllTheaters());
                return "admin/show-theaters";
            default:
                return "redirect:/";
            }
        } else {
            return "redirect:/admin";
        }
    }

    @GetMapping("/admin/{directory}/delete/{id}")
    public String deleteThis(@PathVariable(name = "directory") String directory, @PathVariable(name = "id") String id, RedirectAttributes ra){
        if(session.getAttribute("logged") != null && session.getAttribute("logged").equals(true)) {
            ra.addFlashAttribute("message", "#"+id+" deleted");
            switch (directory) {
                case ("titles"):
                    perfRepo.deleteTitle(Integer.parseInt(id));
                    return "redirect:/admin/titles";
                case ("performances"):
                    perfRepo.deletePerformance(Integer.parseInt(id));
                    return "redirect:/admin/performances";
                case ("theaters"):
                    perfRepo.deleteTheater(Integer.parseInt(id));
                    return "redirect:/admin/theaters";
                case ("bookings"):
                    perfRepo.deleteBooking(Integer.parseInt(id));
                    return "redirect:/admin/performances";
                default:
                    return "redirect:/";
            }
        } else {
            return "redirect:/admin";
        }
    }

    @GetMapping("/admin/{directory}/edit/{id}")
    public String editThis(@PathVariable(name = "directory") String directory, @PathVariable(name = "id") String id, Model model){
        if(session.getAttribute("logged") != null && session.getAttribute("logged").equals(true)) {
            switch (directory) {
                case ("titles"):
                    model.addAttribute("titleForm", perfRepo.findTitle(Integer.parseInt(id)));
                    return "admin/edit-title";
                case ("performances"):
                    model.addAttribute("theaters", perfRepo.findAllTheaters());
                    model.addAttribute("titles", perfRepo.findAllTitles(false, null));
                    model.addAttribute("performanceForm", perfRepo.findPerformance(Integer.parseInt(id)));
                    return "admin/edit-performance";
                case ("theaters"):
                    model.addAttribute("theaterForm", perfRepo.findTheater(Integer.parseInt(id)));
                    return "admin/edit-theater";
                default:
                    return "redirect:/";
            }
        } else {
            return "redirect:/admin";
        }
    }

    @GetMapping("/admin/{directory}/add")
    public String saveThis(@PathVariable(name = "directory") String directory, Model model) {
        if(session.getAttribute("logged") != null && session.getAttribute("logged").equals(true)) {
            switch (directory) {
                case ("titles"):
                    model.addAttribute("titleForm", new Title());
                    return "admin/add-title";
                case ("performances"):
                    Performance performance = new Performance();
                    performance.setTheater(new Theater());
                    performance.setTitle(new Title());

                    model.addAttribute("theaters", perfRepo.findAllTheaters());
                    model.addAttribute("titles", perfRepo.findAllTitles(false, null));
                    model.addAttribute("performanceForm", performance);
                    return "admin/add-performance";
                case ("theaters"):
                    model.addAttribute("theaterForm", new Theater());
                    return "admin/add-theater";
                default:
                    return "redirect:/";
            }
        } else {
            return "redirect:/admin";
        }
    }

    @PostMapping("/admin/titles/add-title")
    public String addTitle(@ModelAttribute Title title, BindingResult result, RedirectAttributes ra) {
        if(session.getAttribute("logged") != null && session.getAttribute("logged").equals(true)) {
            if (result.hasErrors()) {
                ra.addFlashAttribute("message", "check input format");
                return "redirect:/admin/titles/add";
            } else {
                ra.addFlashAttribute("message", "new title added");
                perfRepo.addTitle(title);
                return "redirect:/admin/titles";
            }
        } else {
            return "redirect:/admin";
        }
    }

    @PostMapping("/admin/titles/update-title")
    public String updateTitle(@ModelAttribute Title title, BindingResult result, RedirectAttributes ra){
        if(session.getAttribute("logged") != null && session.getAttribute("logged").equals(true)) {
            if (result.hasErrors()) {
                ra.addFlashAttribute("message", "check input format");
                return "redirect:/admin/titles/edit/"+title.getId();
            } else {
                ra.addFlashAttribute("message", "title updated");
                perfRepo.updateTitle(title);
                return "redirect:/admin/titles";
            }
        } else {
            return "redirect:/admin";
        }
    }

    @PostMapping("/admin/performances/add-performance")
    public String addPerformance(@ModelAttribute Performance performance, BindingResult result, RedirectAttributes ra) {
        if(session.getAttribute("logged") != null && session.getAttribute("logged").equals(true)) {
            if (result.hasErrors()) {
                ra.addFlashAttribute("message", "check input format");
                return "redirect:/admin/performances/add";
            } else {
                ra.addFlashAttribute("message", "new performance added");
                perfRepo.addPerformance(performance);
                return "redirect:/admin/performances";
            }
        } else {
            return "redirect:/admin";
        }
    }

    @PostMapping("/admin/performances/update-performance")
    public String updatePerformance(@ModelAttribute Performance performance, BindingResult result, RedirectAttributes ra){
        if(session.getAttribute("logged") != null && session.getAttribute("logged").equals(true)) {
            if (result.hasErrors()) {
                ra.addFlashAttribute("message", "check input format");
                return "redirect:/admin/performances/edit/"+performance.getId();
            } else {
                ra.addFlashAttribute("message", "performance updated");
                perfRepo.updatePerformance(performance);
                return "redirect:/admin/performances";
            }
        } else {
            return "redirect:/admin";
        }
    }

    @PostMapping("/admin/theaters/add-theater")
    public String addTheater(@ModelAttribute Theater theater, BindingResult result, RedirectAttributes ra) {
        if(session.getAttribute("logged") != null && session.getAttribute("logged").equals(true)) {
            if (result.hasErrors()) {
                ra.addFlashAttribute("message", "check input format");
                return "redirect:/admin/theaters/add";
            } else {
                ra.addFlashAttribute("message", "new theater added");
                perfRepo.addTheater(theater);
                return "redirect:/admin/theaters";
            }
        } else {
            return "redirect:/admin";
        }
    }

    @PostMapping("/admin/theaters/update-theater")
    public String updateTheater(@ModelAttribute Theater theater, BindingResult result, RedirectAttributes ra){
        if (result.hasErrors()) {
            ra.addFlashAttribute("message", "check input format");
            return "redirect:/admin/theaters/edit/"+theater.getId();
        } else {
            ra.addFlashAttribute("message", "theater updated");
            perfRepo.updateTheater(theater);
            return "redirect:/admin/theaters";
        }
    }

    @GetMapping("/admin/bookings/{id}")
    public String bookingsFor(@PathVariable(name = "id") String id, @RequestParam(required = false, value = "phone") String phone, Model model){
        if(session.getAttribute("logged") != null && session.getAttribute("logged").equals(true)) {
            model.addAttribute("bookings", perfRepo.findBookingsFor(Integer.parseInt(id), phone));
            return "admin/show-bookings";
        } else {
            return "redirect:/admin";
        }
    }
}