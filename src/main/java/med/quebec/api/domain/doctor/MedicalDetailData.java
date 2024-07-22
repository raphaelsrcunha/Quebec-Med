package med.quebec.api.domain.doctor;

import med.quebec.api.domain.address.Address;

public record MedicalDetailData(Long id, String name, String email, String crm, MedicalSpecialty specialty, Address address) {

    public MedicalDetailData(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getCrm(), doctor.getPhone(), doctor.getSpecialty(), doctor.getAddress());
    }

}
