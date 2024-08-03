package med.quebec.api.domain.doctor;

import med.quebec.api.domain.address.Address;
import med.quebec.api.domain.address.AddressData;
import med.quebec.api.domain.appointment.Appointment;
import med.quebec.api.domain.appointment.CancellationReason;
import med.quebec.api.domain.patient.Patient;
import med.quebec.api.domain.patient.PatientRegistrationData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    @DisplayName("Should return “null” when the only registered doctor is not available on the date")
    void chooseRandomDoctorAvailableOnThatDateScenario1() {

        // given or arrange
        var nextMonday10am = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);
        var doctor = postDoctor("Doctor", "doctor@quebecmed.com", "123456", MedicalSpecialty.CARDIOLOGY);
        var patient = postPatient("Patient", "patient@patient.com", "12345678901");
        postAppointment(doctor, patient, nextMonday10am);

        //when or act
        var availableDoctor = doctorRepository.chooseRandomDoctorAvailableOnThatDate(MedicalSpecialty.CARDIOLOGY, nextMonday10am);

        // then or assert
        assertThat(availableDoctor).isNull();

    }

    @Test
    @DisplayName("Should return “doctor” when doctor is available on the date")
    void chooseRandomDoctorAvailableOnThatDateScenario2() {

        // given or arrange
        var nextMonday10am = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10,0);
        var doctor = postDoctor("Doctor", "doctor@quebecmed.com", "123456", MedicalSpecialty.CARDIOLOGY);

        // when or act
        var availableDoctor = doctorRepository.chooseRandomDoctorAvailableOnThatDate(MedicalSpecialty.CARDIOLOGY, nextMonday10am);

        // then or assert
        assertThat(availableDoctor).isEqualTo(doctor);

    }

    private void postAppointment(Doctor doctor, Patient patient, LocalDateTime apptDate) {
        entityManager.persist(new Appointment(null, doctor, patient, apptDate, CancellationReason.DOCTOR_CANCELED));
    }

    private Doctor postDoctor(String name, String email, String crm, MedicalSpecialty medicalSpecialty) {
        var doctor = new Doctor(medicalData(name, email, crm, medicalSpecialty));
        entityManager.persist(doctor);
        return doctor;
    }

    private Patient postPatient(String name, String email, String cpf){
        var patient = new Patient(patientData(name, email, cpf));
        entityManager.persist(patient);
        return patient;
    }

    private MedicalRegistrationData medicalData(String name, String email, String crm, MedicalSpecialty medicalSpecialty) {
        return new MedicalRegistrationData(name, email, "1234567890", crm, medicalSpecialty, addressData());
    }

    private PatientRegistrationData patientData(String name, String email, String cpf) {
        return new PatientRegistrationData(name, email, "1234567890", cpf, addressData());
    }

    private AddressData addressData() {
        return new AddressData(
                "Rue Jean Baptiste",
                "Ville Marie",
                "H9O 2U9",
                "Montréal",
                "QC",
                null,
                null
        );
    }

}