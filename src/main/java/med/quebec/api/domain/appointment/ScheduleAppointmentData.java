package med.quebec.api.domain.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record ScheduleAppointmentData (

        Long doctorId,

        @NotNull
        Long PatientId,

        @NotNull
        @Future
        LocalDateTime apptDate) {
}
