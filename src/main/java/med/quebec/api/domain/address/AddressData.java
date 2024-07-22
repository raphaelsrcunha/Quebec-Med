package med.quebec.api.domain.address;

import jakarta.validation.constraints.NotBlank;

public record AddressData(

        @NotBlank
        String street,

        @NotBlank
        String district,

        @NotBlank
        //@Pattern(regexp = "\\d{8}")
        String postalCode,

        @NotBlank
        String city,

        @NotBlank
        String state,

        String number,
        String complement) {
}
