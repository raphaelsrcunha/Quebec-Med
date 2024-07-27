package med.quebec.api.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

// Quando herdamos de JpaRepository precisamos passar os generics entre <>. O primeiro é o tipo da entidade que
// esse repository vai trabalhar e o segundo é qual é o tipo do atributo da chave primária dessa entidade
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByActiveTrue(Pageable pagination);

    // We could do this using java code or by querying the DB directly, which is more efficient. So we chose that.
    @Query("""
            select d from Doctor d
            where
            d.active = true
            and
            d.specialty = :medicalSpecialty
            and
            d.id not in(
                select a.doctor.id from Appointment a
                where
                a.apptDate = :apptDate
            )
            order by rand()
            limit 1
            """)
    Doctor chooseRandomDoctorAvailableOnThatDate(MedicalSpecialty medicalSpecialty, LocalDateTime apptDate);

    @Query("""
            SELECT d.active 
            FROM Doctor d
            WHERE d.id = :doctorId
            """)
    Boolean isActiveById(Long doctorId);
}