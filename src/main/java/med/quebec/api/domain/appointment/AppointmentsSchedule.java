package med.quebec.api.domain.appointment;

import med.quebec.api.domain.ExceptionValidation;
import med.quebec.api.domain.doctor.Doctor;
import med.quebec.api.domain.doctor.DoctorRepository;
import med.quebec.api.domain.doctor.MedicalSpecialty;
import med.quebec.api.domain.patient.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentsSchedule {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    public void schedule(ScheduleAppointmentData data){

        if(!patientRepository.existsById(data.patientId())) {
            throw new ExceptionValidation("Patient id doesn't exist!");
        }

        if(data.doctorId() != null && !doctorRepository.existsById(data.doctorId())) {
            throw new ExceptionValidation("Doctor id doesn't exist!");
        }

        var patient = patientRepository.getReferenceById(data.patientId());
        var doctor = chooseDoctor(data);
        var appointment = new Appointment(null, doctor, patient, data.apptDate());

        appointmentRepository.save(appointment);

    }

    private Doctor chooseDoctor(ScheduleAppointmentData data) {

        if(data.doctorId() != null) {
            return doctorRepository.getReferenceById(data.doctorId());
        }

        if(data.medicalSpecialty() == null) {
            throw new ExceptionValidation("Doctor specialty is required when 'doctor' field isn't filled");
        }

        return doctorRepository.chooseRandomDoctorAvailableOnThatDate(data.medicalSpecialty(), data.apptDate());

    }

}
