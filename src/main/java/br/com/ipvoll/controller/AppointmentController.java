package br.com.ipvoll.controller;

import br.com.ipvoll.domain.appointment.AppointmentDTO;
import br.com.ipvoll.domain.appointment.AppointmentDTOCancellation;
import br.com.ipvoll.domain.appointment.AppointmentDTODetaild;
import br.com.ipvoll.domain.appointment.AppointmentSchedule;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("appointment")
public class AppointmentController {

    @Autowired
    private AppointmentSchedule appointmentSchedule;

    @PostMapping
    @Transactional
    public ResponseEntity<AppointmentDTODetaild> toSchedule(@RequestBody @Valid AppointmentDTO appointmentDTO) {
        var appointmentDTOdetaild = appointmentSchedule.toSchedule(appointmentDTO);
        return ResponseEntity.ok(appointmentDTOdetaild);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Object> cancelAppointment (@RequestBody @Valid AppointmentDTOCancellation appointmentDTOCancellation) {
        appointmentSchedule.cancelAppointment(appointmentDTOCancellation);
        return ResponseEntity.noContent().build();
    }
}
