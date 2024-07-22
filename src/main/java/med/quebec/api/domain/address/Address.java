package med.quebec.api.domain.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String street;
    private String district;
    private String postalCode;
    private String city;
    private String state;
    private String number;
    private String complement;

    public Address(AddressData address) {
        this.city = address.city();
        this.complement = address.complement();
        this.district = address.district();
        this.number = address.number();
        this.state = address.state();
        this.postalCode = address.postalCode();
        this.street = address.street();
    }

    public void updateData(AddressData address) {
        if(this.city != null){
            this.city = address.city();
        }

        if(this.district != null){
            this.district = address.district();
        }

        if(this.postalCode != null){
            this.postalCode = address.postalCode();
        }

        if(this.street != null){
            this.street = address.street();
        }

        if(this.state != null){
            this.state = address.state();
        }

        if(this.number != null){
            this.number = address.number();
        }

        if(this.complement != null){
            this.complement = address.complement();
        }

    }
}
