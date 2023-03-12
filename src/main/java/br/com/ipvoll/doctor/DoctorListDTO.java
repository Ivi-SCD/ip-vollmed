package br.com.ipvoll.doctor;

public record DoctorListDTO(String name, String email, String crm, Specialty specialty) {

    public DoctorListDTO(Doctor doctor) {
        this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }

}
