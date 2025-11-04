DROP TABLE IF EXISTS dish;

CREATE TABLE dish (
                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      category VARCHAR(100) NOT NULL,
                      price DECIMAL(10, 2) NOT NULL
);