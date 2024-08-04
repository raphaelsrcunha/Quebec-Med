package med.quebec.api.domain.appointment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    boolean existsByDoctorIdAndApptDate(Long doctorId, LocalDateTime appt_date);
    boolean existsByPatientIdAndApptDateBetween(Long patientId, LocalDateTime firstHour, LocalDateTime lastHour);

}
