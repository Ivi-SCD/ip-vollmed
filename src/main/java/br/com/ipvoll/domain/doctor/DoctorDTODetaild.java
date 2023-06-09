package br.com.ipvoll.domain.doctor;

import br.com.ipvoll.domain.address.Address;

public record DoctorDTODetaild(Long id, String name, String email, String crm, Specialty specialty, Address address) {

    public DoctorDTODetaild(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty(), doctor.getAddress());
    }
}
