package br.com.ipvoll.patient;

public record PatientListDTO(Long id, String name, String email, String ssn) {

    public PatientListDTO(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getSsn());
    }

}
