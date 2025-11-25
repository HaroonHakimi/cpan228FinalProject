DROP TABLE IF EXISTS dish;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS secondary_dish;

CREATE TABLE dish (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      category VARCHAR(100) NOT NULL,
                      price DECIMAL(10, 2) NOT NULL
);

CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       role VARCHAR(20) NOT NULL,
                       enabled BOOLEAN NOT NULL
);

CREATE TABLE secondary_dish (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                name VARCHAR(255) NOT NULL,
                                category VARCHAR(100) NOT NULL,
                                price DECIMAL(10, 2) NOT NULL,
                                source_service VARCHAR(100)
);