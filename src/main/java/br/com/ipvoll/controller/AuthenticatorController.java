package br.com.ipvoll.controller;

import br.com.ipvoll.domain.user.AuthenticatorDTO;
import br.com.ipvoll.domain.user.User;
import br.com.ipvoll.infra.security.TokenJWTDTO;
import br.com.ipvoll.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticatorController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenJWTDTO> doLogin(@RequestBody @Valid AuthenticatorDTO authenticatorDTO) {

        var authenticationToken = new UsernamePasswordAuthenticationToken(authenticatorDTO.login(), authenticatorDTO.password());
        var authentication = authenticationManager.authenticate(authenticationToken);

        var tokenJWT = tokenService.createToken((User) authentication.getPrincipal());

        return ResponseEntity.ok(new TokenJWTDTO(tokenJWT));
    }
}
