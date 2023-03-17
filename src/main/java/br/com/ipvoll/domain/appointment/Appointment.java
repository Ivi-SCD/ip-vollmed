package br.com.ipvoll.domain.appointment;

import br.com.ipvoll.domain.doctor.Doctor;
import br.com.ipvoll.domain.patient.Patient;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "appointments")
@Entity(name="Appointment")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="patient_id")
    private Patient patient;

    private LocalDateTime date;

    @Column(name = "reason_cancellation")
    @Enumerated(EnumType.STRING)
    private ReasonCancellation reasonCacellation;

    public void cancelAppointment(ReasonCancellation reasonCancellation) {
        this.reasonCacellation = reasonCancellation;
    }
}
