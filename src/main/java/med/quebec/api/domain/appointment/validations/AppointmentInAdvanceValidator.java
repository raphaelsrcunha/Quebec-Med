package med.quebec.api.domain.appointment.validations;

import jakarta.validation.ValidationException;
import med.quebec.api.domain.appointment.ScheduleAppointmentData;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class AppointmentInAdvanceValidator implements AppointmentScheduleValidator {

    public void validate(ScheduleAppointmentData data) {
        var appointmentDate = data.apptDate();
        var now = LocalDateTime.now();
        var diffInMinutes = Duration.between(now, appointmentDate).toMinutes();

        if(diffInMinutes < 30) {
            throw new ValidationException("Appointments must be scheduled more than 30 minutes in advance");
        }
    }

}
