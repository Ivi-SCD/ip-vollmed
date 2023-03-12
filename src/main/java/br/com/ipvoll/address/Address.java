package br.com.ipvoll.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
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
}
