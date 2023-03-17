CREATE TABLE appointments (
    id SERIAL NOT NULL PRIMARY KEY ,
    doctor_id BIGINT NOT NULL REFERENCES doctors(id),
    patient_id BIGINT NOT NULL REFERENCES patients(id),
    date TIMESTAMP NOT NULL
);