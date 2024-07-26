package med.quebec.api.domain.appointment;

import java.time.LocalDateTime;

public record detailingAppointmentData(Long id, Long doctorId, Long patientId, LocalDateTime date) {
}
