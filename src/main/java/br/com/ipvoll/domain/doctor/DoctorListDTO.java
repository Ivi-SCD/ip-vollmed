package br.com.ipvoll.domain.doctor;

public record DoctorListDTO(Long id, String name, String email, String crm, Specialty specialty) {

    public DoctorListDTO(Doctor doctor) {
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }

}
