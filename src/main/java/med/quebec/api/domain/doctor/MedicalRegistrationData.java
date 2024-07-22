package med.quebec.api.domain.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.quebec.api.domain.address.AddressData;

public record MedicalRegistrationData(

        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String phone,

        @NotBlank
        //@Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull
        MedicalSpecialty specialty,

        @NotNull @Valid AddressData address) {
}
