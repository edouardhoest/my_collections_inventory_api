CREATE DATABASE my_collector_inventory_db;

CREATE TABLE IF NOT EXISTS manga (
                                     id SERIAL PRIMARY KEY,
                                     title VARCHAR(100) NOT NULL UNIQUE,
    author VARCHAR(100) NOT NULL,
    description VARCHAR(1000),
    imageUrl VARCHAR(1000),
    categories VARCHAR(10000),
    note DECIMAL(5, 2)
    );