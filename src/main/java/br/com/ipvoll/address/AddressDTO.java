package br.com.ipvoll.address;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AddressDTO(
        @NotBlank
        String publicplace,
        @NotBlank
        String neighborhood,
        @NotBlank
        @Pattern(regexp = "\\d{8}")
        String zip,
        @NotBlank
        String city,
        @NotBlank
        String state,
        String number,
        String complement) {
}
