package br.com.ipvoll.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findAllByActiveTrue(Pageable pagination);

    @Query(value = """
            SELECT d FROM Doctor d
            WHERE d.active = true
            AND d.specialty = :specialty
            AND d.id NOT IN (
                SELECT a.doctor.id FROM Appointment a
                WHERE a.date = :date
                )
            ORDER BY random()
            LIMIT 1
            """)
    Doctor chooseRandomDoctorFreenOnDate(Specialty specialty, LocalDateTime date);

    @Query(value = """
            SELECT d.active FROM Doctor d
            WHERE d.id = :idDoctor
            """)
    boolean findActiveById(Long idDoctor);
}
