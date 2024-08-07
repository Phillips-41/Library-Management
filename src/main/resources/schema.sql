--create table if not exists publisher(
--    publisherId INT PRIMARY KEY AUTO_INCREMENT,
--    publisherName VARCHAR(200)
--);
--
--create table if not exists author(
--    authorId INT PRIMARY KEY AUTO_INCREMENT,
--    authorName VARCHAR(200)
--);
--
--create table if not exists book(
--     id  INT PRIMARY KEY AUTO_INCREMENT,
--     name VARCHAR(200),
--     imageUrl VARCHAR(200),
--     publisherId INT,
--     FOREIGN KEY (publisherId)  REFERENCES publisher(publisherId)
--);
----
--create table if not exists book_author (
--  bookId INT,
--  authorId INT,
--  PRIMARY KEY (bookId, authorId),
--  FOREIGN KEY (bookId) REFERENCES book(id),
--  FOREIGN KEY (authorId) REFERENCES author(authorId)
--);
CREATE TABLE IF NOT EXISTS publisher(
    publisherId SERIAL PRIMARY KEY,
    publisherName VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS author(
    authorId SERIAL PRIMARY KEY,
    authorName VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS book(
     id  SERIAL PRIMARY KEY,
     name VARCHAR(200),
     imageUrl VARCHAR(200),
     publisherId INT,
     FOREIGN KEY (publisherId) REFERENCES publisher(publisherId)
);

CREATE TABLE IF NOT EXISTS book_author (
  bookId INT,
  authorId INT,
  PRIMARY KEY (bookId, authorId),
  FOREIGN KEY (bookId) REFERENCES book(id),
  FOREIGN KEY (authorId) REFERENCES author(authorId)
);

