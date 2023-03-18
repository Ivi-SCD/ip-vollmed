package br.com.ipvoll.domain.appointment.validations;

import br.com.ipvoll.domain.ValidationException;
import br.com.ipvoll.domain.appointment.AppointmentDTO;
import br.com.ipvoll.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class ValidatorActivePatient implements ValidatorAppointmentSchedule {

    @Autowired
    private PatientRepository patientRepository;

    public void validate (AppointmentDTO appointmentDTO) {
        var patientIsActive = patientRepository.findActiveById(appointmentDTO.idPatient());

        if(!patientIsActive) {
            throw new ValidationException("Consultation cannot be scheduled with an inactive patient");
        }
    }
}
