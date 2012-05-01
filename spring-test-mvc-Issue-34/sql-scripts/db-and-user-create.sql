CREATE DATABASE some_db CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE USER 'test' IDENTIFIED BY 'test';
GRANT ALL ON some_db.* TO 'test'@'%' IDENTIFIED BY 'test';
GRANT ALL ON some_db.* TO 'test'@'localhost' IDENTIFIED BY 'test';
FLUSH PRIVILEGES;