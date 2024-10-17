CREATE DATABASE my_collector_inventory_db;

CREATE TABLE IF NOT EXISTS manga (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     title VARCHAR(1000) NOT NULL UNIQUE,
    author VARCHAR(1000) NOT NULL,
    description TEXT,
    imageUrl VARCHAR(1000),
    categories VARCHAR(1000),
    status VARCHAR(500)
    );