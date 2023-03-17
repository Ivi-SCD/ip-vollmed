package br.com.ipvoll.domain.appointment;

import java.time.LocalDateTime;

public record AppointmentDTODetaild(Long id, Long idDoctor, Long idPatient, LocalDateTime date) {
}
