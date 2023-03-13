package br.com.ipvoll.patient;

import br.com.ipvoll.address.AddressDTO;
import jakarta.validation.Valid;
public record PatientDTOUpdate(

        Long id,
        String name,
        String phonenumber,
        @Valid AddressDTO address

) {
}
