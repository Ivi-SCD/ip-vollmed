package br.com.ipvoll.domain.doctor;

import br.com.ipvoll.domain.address.AddressDTO;
import br.com.ipvoll.domain.appointment.Appointment;
import br.com.ipvoll.domain.patient.Patient;
import br.com.ipvoll.domain.patient.PatientDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;



@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    @DisplayName("Must return null when the only one doctor registered is not available on date")
    void chooseRandomDoctorFreenOnDateScenaryOne() {
        var nextMondayAtTenAM = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);
        var doctorAvailable = doctorRepository.chooseRandomDoctorFreenOnDate(Specialty.CARDIOLOGY, nextMondayAtTenAM);

        var doctor = registerDoctor("Rodrigo", "rodrigo@ipvoll.doc", "123123", Specialty.CARDIOLOGY);
        var patient = registerPatient("Alexa", "alexa@ipvoll.pat",  "00000000000000");
        registerAppointment(doctor, patient, nextMondayAtTenAM);

        assertThat(doctorAvailable).isNull();
    }

    @Test
    @DisplayName("Must return Doctor when he is the only one available on date")
    void chooseRandomDoctorFreenOnDateScenaryTwo() {
        var nextMondayAtTenAM = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);
        var doctor = registerDoctor("Rodrigo", "rodrigo@ipvoll.doc", "123123", Specialty.CARDIOLOGY);

        var doctorAvailable = doctorRepository.chooseRandomDoctorFreenOnDate(Specialty.CARDIOLOGY, nextMondayAtTenAM);
        assertThat(doctorAvailable).isEqualTo(doctor);
    }

    @Test
    @DisplayName("Must return null when does not exist any doctor on database")
    void chooseRandomDoctorFreenOnDateScenaryThree() {
        var nextMondayAtTenAM = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10,0);
        var doctorAvailable = doctorRepository.chooseRandomDoctorFreenOnDate(Specialty.CARDIOLOGY, nextMondayAtTenAM);
        assertThat(doctorAvailable).isNull();
    }

    private void registerAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
        testEntityManager.persist(new Appointment(null, doctor, patient, date, null));
    }

    private Doctor registerDoctor(String name, String email, String crm, Specialty specialty) {
        var doctor = new Doctor(doctorDTO(name, email, crm, specialty));
        testEntityManager.persist(doctor);
        return doctor;
    }

    private Patient registerPatient(String name, String email, String ssn) {
        var patient = new Patient(patientDTO(name, email, ssn));
        testEntityManager.persist(patient);
        return patient;
    }

    private DoctorDTO doctorDTO(String name, String email, String crm, Specialty specialty) {
        return new DoctorDTO(
                name,
                email,
                "619999999",
                crm,
                specialty,
                addressDTO()
        );
    }

    private PatientDTO patientDTO(String name, String email, String ssn) {
        return new PatientDTO(
                name,
                email,
                "619999999",
                ssn,
                addressDTO()
        );
    }

    private AddressDTO addressDTO() {
        return new AddressDTO(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}