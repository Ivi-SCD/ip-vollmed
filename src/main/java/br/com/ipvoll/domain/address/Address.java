package br.com.ipvoll.domain.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String publicplace;
    private String neighborhood;
    private String zip;
    private String city;
    private String state;
    private String number;
    private String complement;

    public Address(AddressDTO address) {
        this.publicplace = address.publicplace();
        this.neighborhood = address.neighborhood();
        this.zip = address.zip();
        this.city = address.city();
        this.state = address.state();
        this.number = address.number();
        this.complement = address.complement();
    }

    public void updateInfo(AddressDTO address) {
        if(address.publicplace() != null) {
            this.publicplace = address.publicplace();
        }
        if(address.neighborhood() != null) {
            this.neighborhood = address.neighborhood();
        }
        if(address.zip() != null) {
            this.zip = address.zip();
        }
        if(address.city() != null) {
            this.city = address.city();
        }
        if(address.state() != null) {
            this.state = address.state();
        }
        if(address.number() != null) {
            this.number = address.number();
        }
        if(address.complement() != null) {
            this.complement = address.complement();
        }

    }
}
