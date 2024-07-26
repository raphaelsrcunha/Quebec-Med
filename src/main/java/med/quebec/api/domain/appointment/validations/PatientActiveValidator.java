package med.quebec.api.domain.appointment.validations;

import jakarta.validation.ValidationException;
import med.quebec.api.domain.appointment.ScheduleAppointmentData;
import med.quebec.api.domain.patient.PatientRepository;

public class PatientActiveValidator {

    PatientRepository patientRepository;

    public void validator(ScheduleAppointmentData data) {

        var isPatientActive = patientRepository.existsById(data.patientId());

        if(!isPatientActive) {
            throw new ValidationException("Patient must be active to schedule an appointment");
        }

    }

}
