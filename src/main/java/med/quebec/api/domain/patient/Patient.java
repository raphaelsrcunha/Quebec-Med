package med.quebec.api.domain.patient;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.quebec.api.domain.address.Address;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Patient")
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String phone;
    private String cpf;

    @Embedded
    private Address address;

    private Boolean active;

    public Patient(PatientRegistrationData data) {
        this.active = true;
        this.name = data.name();
        this.phone = data.phone();
        this.cpf = data.cpf();
        this.email = data.email();
        this.address = new Address(data.address());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void inactivate() {
        this.active = false;
    }

    public void updateData(PatientUpdateData data) {

        if(data.name() != null){
            this.name = data.name();
        }

        if(data.phone() != null){
            this.phone = data.phone();
        }

        if(data.address() != null){
            this.address.updateData(data.address());
        }

    }
}
