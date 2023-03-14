CREATE TABLE users (
    id SERIAL NOT NULL,
    login VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,

    PRIMARY KEY(id)
);