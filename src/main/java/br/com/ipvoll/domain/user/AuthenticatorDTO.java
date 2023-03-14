package br.com.ipvoll.domain.user;

public record AuthenticatorDTO(
        String login,
        String password) {
}
