package br.com.ipvoll.domain.appointment.validations;

import br.com.ipvoll.domain.ValidationException;
import br.com.ipvoll.domain.appointment.AppointmentDTO;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component

public class ValidatorClinicOpeningHours implements ValidatorAppointmentSchedule {

    public void validate (AppointmentDTO appointmentDTO) {
        var consultationDate = appointmentDTO.date();

        var sunday = consultationDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeOpening = consultationDate.getHour() < 7;

        var afterEndingHour = consultationDate.getHour() > 18;

        if(sunday || beforeOpening || afterEndingHour) {
            throw new ValidationException("Consultation outside clinic hours");
        }
    }
}
