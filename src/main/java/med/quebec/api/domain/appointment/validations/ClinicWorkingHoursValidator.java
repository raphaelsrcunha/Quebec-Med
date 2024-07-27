package med.quebec.api.domain.appointment.validations;

import jakarta.validation.ValidationException;
import med.quebec.api.domain.appointment.ScheduleAppointmentData;

import java.time.DayOfWeek;

public class ClinicWorkingHours {

    public void validate(ScheduleAppointmentData data) {
        var apptDate = data.apptDate();
        var sunday = apptDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeOpening = apptDate.getHour() < 7;
        var afterClosing = apptDate.getHour() > 18;

        if (sunday || beforeOpening || afterClosing) {
            throw new ValidationException("Appointment out of working hours");
        }
    }

}