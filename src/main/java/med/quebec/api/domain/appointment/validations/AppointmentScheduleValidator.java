package med.quebec.api.domain.appointment.validations;

import med.quebec.api.domain.appointment.ScheduleAppointmentData;

public interface AppointmentScheduleValidator {

    void validate(ScheduleAppointmentData data);

}
