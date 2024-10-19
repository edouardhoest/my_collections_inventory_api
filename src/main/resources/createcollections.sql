CREATE
DATABASE my_collector_inventory_db;

CREATE TABLE IF NOT EXISTS collection
(
    id
    SERIAL
    PRIMARY
    KEY,
    idUser
    VARCHAR
(
    100
) NOT NULL UNIQUE,
    idManga VARCHAR(100)
    );