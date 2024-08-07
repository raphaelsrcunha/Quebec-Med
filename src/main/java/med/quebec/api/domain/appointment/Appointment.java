package med.quebec.api.domain.appointment;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.quebec.api.domain.doctor.Doctor;
import med.quebec.api.domain.patient.Patient;

import java.time.LocalDateTime;

@Table(name="appointments")
@Entity(name="Appointment")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    private LocalDateTime apptDate;

    @Column(name="cancellation_reason")
    private CancellationReason cancellationReason;

    public void cancel(CancellationReason cancellationReason) {
        this.cancellationReason = cancellationReason;
    }
}
