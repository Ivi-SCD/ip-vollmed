package br.com.ipvoll.domain.appointment;

import java.time.LocalDateTime;

public record AppointmentDTODetaild(Long id, Long idDoctor, Long idPatient, LocalDateTime date) {
    public AppointmentDTODetaild(Appointment appointment) {
        this(appointment.getId(), appointment.getDoctor().getId(), appointment.getPatient().getId(), appointment.getDate());
    }
}
