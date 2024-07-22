package med.quebec.api.domain.doctor;

import jakarta.validation.constraints.NotNull;
import med.quebec.api.domain.address.AddressData;

//Deveria inserir um @Valid antes de AddressData para validar também os campos de endereço, caso ocorra mudança?
public record MedicalUpdateData(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressData address) {
}
