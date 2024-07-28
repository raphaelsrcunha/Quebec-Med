package med.quebec.api.domain.appointment;

import java.time.LocalDateTime;

public record DetailingAppointmentData(Long id, Long doctorId, Long patientId, LocalDateTime apptDate) {
    public DetailingAppointmentData(Appointment appointment) {
        this(appointment.getId(), appointment.getDoctor().getId(), appointment.getPatient().getId(), appointment.getApptDate());
    }
}
