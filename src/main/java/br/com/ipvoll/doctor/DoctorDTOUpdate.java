package br.com.ipvoll.doctor;

import br.com.ipvoll.address.AddressDTO;
import jakarta.validation.constraints.NotNull;

public record DoctorDTOUpdate(
        @NotNull
        Long id,
        String name,
        String phonenumber,
        AddressDTO address) {
}
