package br.com.ipvoll.domain.appointment.validations;

import br.com.ipvoll.domain.ValidationException;
import br.com.ipvoll.domain.appointment.AppointmentDTO;
import br.com.ipvoll.domain.appointment.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class ValidatorDoctorConsultationAnotherDate implements ValidatorAppointmentSchedule {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate (AppointmentDTO appointmentDTO) {
        var doctorHaveAnotherConsultationInTheSameHour = appointmentRepository.existsByDoctorIdAndDate(appointmentDTO.idDoctor(), appointmentDTO.date());

        if(doctorHaveAnotherConsultationInTheSameHour) {
            throw new ValidationException("Doctor has a another consultation in the same hour");
        }

    }


}
