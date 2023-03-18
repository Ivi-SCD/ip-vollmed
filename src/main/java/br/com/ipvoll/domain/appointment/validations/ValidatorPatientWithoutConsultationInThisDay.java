package br.com.ipvoll.domain.appointment.validations;

import br.com.ipvoll.domain.ValidationException;
import br.com.ipvoll.domain.appointment.AppointmentDTO;
import br.com.ipvoll.domain.appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class ValidatorPatientWithoutConsultationInThisDay implements ValidatorAppointmentSchedule {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate (AppointmentDTO appointmentDTO) {
        var firstHour = appointmentDTO.date().withHour(7);
        var lastHour = appointmentDTO.date().withHour(18);
        var patientHasOtherConsultationInTheDay = appointmentRepository.existsByPatientIdAndDateBetween(appointmentDTO.idPatient(), firstHour, lastHour);
        if(patientHasOtherConsultationInTheDay) {
            throw new ValidationException("Patient already has a consultation scheduled on this day");
        }

    }
}
