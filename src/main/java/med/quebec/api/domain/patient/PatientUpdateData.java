package med.quebec.api.domain.patient;

import jakarta.validation.constraints.NotNull;
import med.quebec.api.domain.address.AddressData;

public record PatientUpdateData(
        @NotNull
        Long id,
        String name,
        String phone,
        AddressData address) {
}
