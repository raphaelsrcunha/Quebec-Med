package med.quebec.api.domain.appointment.validations;

import med.quebec.api.domain.ExceptionValidation;
import med.quebec.api.domain.appointment.ScheduleAppointmentData;
import med.quebec.api.domain.doctor.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorActiveValidator implements AppointmentScheduleValidator {

    @Autowired
    DoctorRepository doctorRepository;

    public void validate(ScheduleAppointmentData data) {

        if(data.doctorId() == null) {
            return;
        }

        var isDoctorActive = doctorRepository.isActiveById(data.doctorId());

        if(!isDoctorActive) {
            throw new ExceptionValidation("This doctor is not active");
        }

    }

}
