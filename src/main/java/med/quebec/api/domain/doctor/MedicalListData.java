package med.quebec.api.domain.doctor;

public record MedicalListData(Long id, String name, String email, String crm, MedicalSpecialty specialty) {

    public MedicalListData(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }

}
