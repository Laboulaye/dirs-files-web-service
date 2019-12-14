
INSERT INTO directories (name) VALUES ("root");
INSERT INTO directories (name, parent_id) VALUES ("november", 1);
INSERT INTO directories (name, parent_id) VALUES ("december", 1);
INSERT INTO directories (name, parent_id) VALUES ("photo", 3);
INSERT INTO directories (name, parent_id) VALUES ("video", 3);
INSERT INTO directories (name, parent_id) VALUES ("temp", 2);
INSERT INTO directories (name, parent_id) VALUES ("X_FILES", 6);
INSERT INTO directories (name, parent_id) VALUES ("innerTemp", 6);
INSERT INTO directories (name, parent_id) VALUES ("new", 4);



INSERT INTO files (name, size, directory_id) VALUES ("pic4.jpg", "4.25 Mb", 4);
INSERT INTO files (name, size, directory_id) VALUES ("pic025.jpg", "3.87 Mb", 4);
INSERT INTO files (name, size, directory_id) VALUES ("pic03.jpg", "2.9 Mb", 4);
INSERT INTO files (name, size, directory_id) VALUES ("pic10.jpg", "5.2 Mb", 4);


INSERT INTO files (name, size, directory_id) VALUES ("Godfather1.avi", "3.87 Gb", 5);
INSERT INTO files (name, size, directory_id) VALUES ("Godfather2.avi", "4.5 Gb", 5);
INSERT INTO files (name, size, directory_id) VALUES ("Godfather3.avi", "4.65 Gb", 5);

INSERT INTO files (name, size, directory_id) VALUES ("function.cpp", "3.57 Kb", 6);
INSERT INTO files (name, size, directory_id) VALUES ("f0008.doc", "26.01 Mb", 6);
INSERT INTO files (name, size, directory_id) VALUES ("F4_00127.pdf", "901.48 Kb", 6);
INSERT INTO files (name, size, directory_id) VALUES ("f4_99.JPG", "1.52 Mb", 6);
INSERT INTO files (name, size, directory_id) VALUES ("F1.txt", "12.57 Kb", 6);
INSERT INTO files (name, size, directory_id) VALUES ("f.txt", "4.28 Kb", 6);
