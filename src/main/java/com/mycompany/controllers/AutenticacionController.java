package com.mycompany.controllers;

import com.mycompany.infra.security.DtoJWT;
import com.mycompany.infra.security.JWTService;
import com.mycompany.modelos.usuario.DtoLoginUsuario;
import com.mycompany.modelos.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JWTService jwtService;

    @PostMapping
    public ResponseEntity autenticarUsuario(@Valid @RequestBody DtoLoginUsuario datosLogin){
        System.out.println("inicio de método");
        Authentication authe = new UsernamePasswordAuthenticationToken(datosLogin.login(), datosLogin.clave());
        System.out.println("datos recibidos");
        var usuarioAutenticado = authenticationManager.authenticate(authe);
        System.out.println("datos autenticados");
        var jwt = jwtService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        System.out.println("token generado");
        return ResponseEntity.ok(new DtoJWT(jwt));
    }
}
