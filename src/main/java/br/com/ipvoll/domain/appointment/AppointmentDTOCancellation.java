package br.com.ipvoll.domain.appointment;

import jakarta.validation.constraints.NotNull;

public record AppointmentDTOCancellation (
        @NotNull
        Long idAppointment,

        @NotNull
        ReasonCancellation reasonCancellation
) {
}
