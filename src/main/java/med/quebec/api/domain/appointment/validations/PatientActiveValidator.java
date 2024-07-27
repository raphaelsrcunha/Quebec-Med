package med.quebec.api.domain.appointment.validations;

import jakarta.validation.ValidationException;
import med.quebec.api.domain.appointment.ScheduleAppointmentData;
import med.quebec.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientActiveValidator implements AppointmentScheduleValidator {

    @Autowired
    PatientRepository patientRepository;

    public void validate(ScheduleAppointmentData data) {

        var isPatientActive = patientRepository.isActiveById(data.patientId());

        if(!isPatientActive) {
            throw new ValidationException("Patient must be active to schedule an appointment");
        }

    }

}
