package med.quebec.api.domain.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.quebec.api.domain.doctor.MedicalSpecialty;

import java.time.LocalDateTime;

public record ScheduleAppointmentData (

        Long doctorId,

        MedicalSpecialty medicalSpecialty,

        @NotNull
        Long patientId,

        @NotNull
        @Future
        LocalDateTime apptDate) {
}
