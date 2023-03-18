package br.com.ipvoll.domain.appointment.validations;

import br.com.ipvoll.domain.ValidationException;
import br.com.ipvoll.domain.appointment.AppointmentDTO;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component

public class ValidatorTimeInAdvance implements ValidatorAppointmentSchedule {

    public void validate(AppointmentDTO appointmentDTO) {
        var consultationDate = appointmentDTO.date();
        var now = LocalDateTime.now();
        var differenceInMinutes = Duration.between(now, consultationDate).toMinutes();

        if(differenceInMinutes < 30) {
            throw new ValidationException("Consultation must be scheduled at least 30 minutes in advance");
        }
    }
}
