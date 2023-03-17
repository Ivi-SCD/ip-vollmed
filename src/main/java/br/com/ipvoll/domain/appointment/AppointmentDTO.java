package br.com.ipvoll.domain.appointment;

import br.com.ipvoll.domain.doctor.Specialty;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentDTO (
        Long idDoctor,
        @NotNull
    Long idPatient,
        @NotNull
    @Future
    LocalDateTime date,

        Specialty specialty) {
}
