package br.com.ipvoll.patient;

import br.com.ipvoll.address.AddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatientDTO(

    @NotBlank
    String name,
    @NotBlank
    @Email
    String email,
    @NotBlank
    String phonenumber,
    @NotBlank
//    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")
    String ssn,
    @NotNull
    @Valid
    AddressDTO address) {
}
