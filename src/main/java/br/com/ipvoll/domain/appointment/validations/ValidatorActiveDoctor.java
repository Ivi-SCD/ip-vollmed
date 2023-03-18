package br.com.ipvoll.domain.appointment.validations;

import br.com.ipvoll.domain.ValidationException;
import br.com.ipvoll.domain.appointment.AppointmentDTO;
import br.com.ipvoll.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class ValidatorActiveDoctor implements ValidatorAppointmentSchedule {

    @Autowired
    private DoctorRepository doctorRepository;

    public void validate(AppointmentDTO appointmentDTO) {
        if(appointmentDTO.idDoctor() == null) {
            return;
        }

        var doctorActive = doctorRepository.findActiveById(appointmentDTO.idDoctor());

        if(!doctorActive) {
            throw new ValidationException("Consultation cannot be scheduled with an inactive doctor");
        }
    }
}
