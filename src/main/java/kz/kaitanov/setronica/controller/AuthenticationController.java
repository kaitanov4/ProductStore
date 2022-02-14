package kz.kaitanov.setronica.controller;

import kz.kaitanov.setronica.config.security.jwt.JwtTokenUtil;
import kz.kaitanov.setronica.model.User;
import kz.kaitanov.setronica.model.dto.AuthenticationDto;
import kz.kaitanov.setronica.model.dto.ResponseDto;
import kz.kaitanov.setronica.model.dto.TokenDto;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthenticationController(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping("/login")
    public ResponseDto<TokenDto> login(@RequestBody @Valid AuthenticationDto authenticationDto) {
        Authentication authenticate = authenticationManager
                .authenticate(
                        new UsernamePasswordAuthenticationToken(
                                authenticationDto.getUsername(), authenticationDto.getPassword()
                        )
                );
        return ResponseDto.ok(new TokenDto(jwtTokenUtil.generateAccessToken((User) authenticate.getPrincipal())));
    }

}