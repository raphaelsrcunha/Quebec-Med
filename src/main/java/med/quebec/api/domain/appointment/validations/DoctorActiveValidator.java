package med.quebec.api.domain.appointment.validations;

import med.quebec.api.domain.ExceptionValidation;
import med.quebec.api.domain.appointment.ScheduleAppointmentData;
import med.quebec.api.domain.doctor.DoctorRepository;

public class DoctorActiveValidator {

    DoctorRepository doctorRepository;

    public void validator(ScheduleAppointmentData data) {

        if(data.doctorId() == null) {
            return;
        }

        var isDoctorActive = doctorRepository.existsById(data.doctorId());

        if(!isDoctorActive) {
            throw new ExceptionValidation("This doctor is not active");
        }

    }

}
