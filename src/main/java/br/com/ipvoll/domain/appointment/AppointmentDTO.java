package br.com.ipvoll.domain.appointment;

import br.com.ipvoll.domain.doctor.Specialty;
import br.com.ipvoll.util.LocalDateTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record AppointmentDTO (
        Long idDoctor,
        @NotNull
        Long idPatient,
        @NotNull
        @Future(message = "Must be a future date.")
        @JsonDeserialize(using = LocalDateTimeDeserializer.class)
        LocalDateTime date,

        Specialty specialty) {
}
