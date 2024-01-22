package com.mycompany.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mycompany.modelos.usuario.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JWTService {

    @Value("${api.security.secret}")
    private String secret;

    public String generarToken(Usuario usuario) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("PeluqueriaCanina")
                    .withSubject(usuario.getUsername())
                    .withClaim("id", usuario.getId())
                    .withExpiresAt(generarFechaDeExpiración())
                    .sign(algorithm);
        } catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }
    private Instant generarFechaDeExpiración() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-05:00"));
    }

    public String getSubject(String jwt){
        if(jwt == null){
            throw new RuntimeException();
        }
        DecodedJWT verifier = null;
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            verifier = JWT.require(algorithm)
                    .withIssuer("PeluqueriaCanina")
                    .build()
                    .verify(jwt);
            verifier.getSubject();
        }catch(JWTVerificationException e){
            System.out.println(e.toString());
        }
        if(verifier.getSubject()== null){
            throw new RuntimeException("Error verificando el JWT");
        }
        return verifier.getSubject();
    }
}
