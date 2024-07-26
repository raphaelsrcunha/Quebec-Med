package med.quebec.api.domain.appointment;

import jakarta.validation.constraints.NotNull;

public record CancelingAppointmentData(

        @NotNull
        Long appointmentId,

        @NotNull
        CancellationReason cancellationReason) {
}
