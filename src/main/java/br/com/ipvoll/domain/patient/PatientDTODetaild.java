package br.com.ipvoll.domain.patient;

import br.com.ipvoll.domain.address.Address;

public record PatientDTODetaild (Long id, String name, String email, String phonenumber, String ssn, Address address) {
    public PatientDTODetaild(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getPhonenumber(), patient.getSsn(), patient.getAddress());
    }
}
