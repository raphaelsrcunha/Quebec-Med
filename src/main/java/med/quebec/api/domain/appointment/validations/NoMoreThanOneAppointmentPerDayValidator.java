package med.quebec.api.domain.appointment.validations;

import med.quebec.api.domain.ExceptionValidation;
import med.quebec.api.domain.appointment.AppointmentRepository;
import med.quebec.api.domain.appointment.ScheduleAppointmentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NoMoreThanOneAppointmentPerDayValidator implements AppointmentScheduleValidator {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public void validate(ScheduleAppointmentData data) {
        var firstHour = data.apptDate().withHour(7);
        var lastHour = data.apptDate().withHour(18);

        var patientHasMoreThanOneAppointmentOnThisDay = appointmentRepository.existsByPatientIdAndApptDateBetween(data.patientId(), firstHour, lastHour);

        if(patientHasMoreThanOneAppointmentOnThisDay) {
            throw new ExceptionValidation("It is not allowed to schedule more than one appointment per day");
        }

    }

}
