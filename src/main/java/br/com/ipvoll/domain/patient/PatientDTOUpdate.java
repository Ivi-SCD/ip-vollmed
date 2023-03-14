package br.com.ipvoll.domain.patient;

import br.com.ipvoll.domain.address.AddressDTO;
import jakarta.validation.Valid;
public record PatientDTOUpdate(

        Long id,
        String name,
        String phonenumber,
        @Valid AddressDTO address

) {
}
