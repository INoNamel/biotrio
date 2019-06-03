SELECT * FROM title_list WHERE display IS true AND genre LIKE '%%' ORDER BY id DESC

SELECT * FROM title_list WHERE id = 1

DELETE FROM title_list WHERE id = 20

INSERT INTO title_list (title, genre, duration, equipment, producer, actors, description) 
VALUES ('Asdqqqqdasd', 'show', 100, if('' = '', null, '3d'), if('' = '', null, 'ahmed'), if('' = '', null, 'qwle, asodkow, awekqwe'), if('' = '', null, 'lorem ipsum'))
