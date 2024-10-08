CREATE DATABASE my_collector_inventory_db;

CREATE TABLE IF NOT EXISTS userInformation (
                                      id BIGSERIAL PRIMARY KEY,
                                      username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
    );