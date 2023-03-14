package br.com.ipvoll.domain.doctor;

import br.com.ipvoll.domain.address.AddressDTO;
import jakarta.validation.constraints.NotNull;

public record DoctorDTOUpdate(
        @NotNull
        Long id,
        String name,
        String phonenumber,
        AddressDTO address) {
}
