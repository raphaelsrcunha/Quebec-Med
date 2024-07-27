package med.quebec.api.domain.appointment.validations;

import med.quebec.api.domain.ExceptionValidation;
import med.quebec.api.domain.appointment.AppointmentRepository;
import med.quebec.api.domain.appointment.ScheduleAppointmentData;
import med.quebec.api.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorWithAnotherAppointmentAtTheSameTimeValidator implements AppointmentScheduleValidator {

    @Autowired
    AppointmentRepository appointmentRepository;

    public void validate(ScheduleAppointmentData data) {

        var hasAppointmentAtTheSameTime = appointmentRepository.existsByDoctorIdAndApptDate(data.doctorId(), data.apptDate());

        if(hasAppointmentAtTheSameTime) {
            throw new ExceptionValidation("This doctor has an appointment at the same time");
        }

    }

}
