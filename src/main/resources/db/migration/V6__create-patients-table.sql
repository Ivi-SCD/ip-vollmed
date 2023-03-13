CREATE TABLE patients
(

    id SERIAL NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phonenumber VARCHAR(9) NOT NULL,
    publicplace  VARCHAR(100) NOT NULL,
    neighborhood VARCHAR(100) NOT NULL,
    zip          VARCHAR(9)   NOT NULL,
    city         VARCHAR(100) NOT NULL,
    state        CHAR(2)      NOT NULL,
    number       VARCHAR(20),
    complement   VARCHAR(100),
    active BOOLEAN NOT NULL,

    PRIMARY KEY (id)

);