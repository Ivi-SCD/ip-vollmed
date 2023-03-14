package br.com.ipvoll.domain.patient;

import br.com.ipvoll.domain.address.AddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PatientDTO(

    @NotBlank(message = "{required.name}")
    String name,
    @NotBlank(message = "{required.email}")
    @Email(message = "{invalid.email}")
    String email,
    @NotBlank(message = "{required.phonenumber}")
    String phonenumber,
    @NotBlank(message = "{required.ssn}")
//    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")
    String ssn,
    @NotNull(message = "{required.address}")
    @Valid
    AddressDTO address) {
}
