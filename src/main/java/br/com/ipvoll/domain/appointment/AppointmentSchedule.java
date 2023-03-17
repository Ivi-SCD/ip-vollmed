package br.com.ipvoll.domain.appointment;

import br.com.ipvoll.domain.ValidationException;
import br.com.ipvoll.domain.doctor.Doctor;
import br.com.ipvoll.domain.doctor.DoctorRepository;
import br.com.ipvoll.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AppointmentSchedule {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    public void toSchedule(AppointmentDTO appointmentDTO) {

        if(!patientRepository.existsById(appointmentDTO.idPatient())) {
            throw new ValidationException("Id of patient does not exist.");
        }
        
        if(appointmentDTO.idDoctor() != null && !doctorRepository.existsById(appointmentDTO.idDoctor())) {
            throw new ValidationException("Id of doctor does not exist.");
        }
        
        var patient = patientRepository.getReferenceById(appointmentDTO.idPatient());
        var doctor = chooseDoctor(appointmentDTO);

        var appointment = new Appointment(null, doctor, patient, appointmentDTO.date(), null);

        appointmentRepository.save(appointment);
    }

    private Doctor chooseDoctor(AppointmentDTO appointmentDTO) {
        if(appointmentDTO.idDoctor() != null) {
            return doctorRepository.getReferenceById(appointmentDTO.idDoctor());
        }

        if(appointmentDTO.specialty() == null) {
            throw new ValidationException("Specialty is mandatory when the doctor is not chosen");
        }

        return doctorRepository.chooseRandomDoctorFreenOnDate(appointmentDTO.specialty(), appointmentDTO.date());

    }

    public void cancelAppointment(AppointmentDTOCancellation appointmentDTOCancellation) {
        if(!appointmentRepository.existsById(appointmentDTOCancellation.idAppointment())) {
            throw new ValidationException("Appointment id not exist");
        }

        var appointment = appointmentRepository.getReferenceById(appointmentDTOCancellation.idAppointment());
        appointment.cancelAppointment(appointmentDTOCancellation.reasonCancellation());
    }
}
