package br.com.ipvoll.domain.doctor;

import br.com.ipvoll.domain.address.AddressDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record DoctorDTO (

        @NotBlank(message = "{required.name}")
        String name,
        @NotBlank(message = "{required.email}")
        @Email(message = "{invalid.email}")
        String email,
        @NotBlank(message = "{required.phonenumber}")
        String phonenumber,
        @NotBlank(message = "{required.crm}")
        @Pattern(regexp = "\\d{4,6}", message = "{invalid.crm}")
        String crm,
        @NotNull(message = "{required.specialty}")
        Specialty specialty,
        @NotNull(message = "{required.address}")
        @Valid
        AddressDTO address) {
}
