DROP DATABASE IF EXISTS TouristInformation;

CREATE DATABASE TouristInformation;

USE TouristInformation;

CREATE TABLE attractions (
    id INT NOT NULL AUTO_INCREMENT,
    attr_name VARCHAR(80),
    city VARCHAR(30),
    category VARCHAR(30),
    price INT,
    longitude FLOAT,
    latitude FLOAT,
    recommended_age FLOAT,
    duration INT,
    PRIMARY KEY (id));

INSERT INTO attractions(attr_name, city, category, price, longitude, latitude, recommended_age, duration)
VALUES ('Kerkyra', 'Budapest', 'restaurant', 1800, 47.484174, 19.060234, 3, 60),
       ('House of Terror', 'Budapest', 'museum', 3000, 47.484110, 19.060172, 3, 120),
       ('Pulitzer', 'Budapest', 'restaurant', 1200, 47.48407, 19.060181, 3, 60),
       ('Heroes'' Square', 'Budapest', 'park', 0, 47.484110, 19.060100, 3, 30),
       ('Budapest Zoo and Botanical Garden', 'Budapest', 'park', 4000, 47.484090, 19.060100, 3, 180);

CREATE TABLE reserved_tickets (
    id INT NOT NULL AUTO_INCREMENT,
    reserve_name VARCHAR(100),
    quantity INT,
    attr_id INT,
    PRIMARY KEY(id),
    FOREIGN KEY(attr_id) REFERENCES attractions(id)
);

INSERT INTO reserved_tickets(reserve_name, quantity, attr_id)
VALUES ('Sally Sinclair',1,4),
       ('Raul Ramirez',5,2),
       ('Andrea Alton',2,2),
       ('Ben Brown',2,5),
       ('Denice Davidson',1,4),
       ('Neil Newman',4,4),
       ('Molly McDonald',3,4),
       ('Zach Zemit',2,5),
       ('Lilly Lindholm',1,5),
       ('Holly Herr',2,4);

