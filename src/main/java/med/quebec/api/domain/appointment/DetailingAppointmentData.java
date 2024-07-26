package med.quebec.api.domain.appointment;

import java.time.LocalDateTime;

public record DetailingAppointmentData(Long id, Long doctorId, Long patientId, LocalDateTime apptDate) {
}