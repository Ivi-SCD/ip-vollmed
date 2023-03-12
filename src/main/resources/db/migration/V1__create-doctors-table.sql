CREATE TABLE  doctors
(

    id           SERIAL       NOT NULL,
    name         VARCHAR(100) NOT NULL,
    email        VARCHAR(100) NOT NULL UNIQUE,
    crm          VARCHAR(6)   NOT NULL UNIQUE,
    specialty    VARCHAR(100) NOT NULL,
    publicplace  VARCHAR(100) NOT NULL,
    neighborhood VARCHAR(100) NOT NULL,
    zip          VARCHAR(9)   NOT NULL,
    city         VARCHAR(100) NOT NULL,
    state        CHAR(2)      NOT NULL,
    number       VARCHAR(20),
    complement   VARCHAR(100),

    PRIMARY KEY (id)

);