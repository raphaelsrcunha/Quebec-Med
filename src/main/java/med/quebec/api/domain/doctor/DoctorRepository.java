package med.quebec.api.domain.doctor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

// Quando herdamos de JpaRepository precisamos passar os generics entre <>. O primeiro é o tipo da entidade que
// esse repository vai trabalhar e o segundo é qual é o tipo do atributo da chave primária dessa entidade
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Page<Doctor> findAllByActiveTrue(Pageable pagination);
}