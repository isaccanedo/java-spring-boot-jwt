package br.com.isaccanedo.app.config.security;

import br.com.isaccanedo.app.entity.Usuario;
import br.com.isaccanedo.app.gateway.database.UsuarioData;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.expiration}")
    private String expiration;

    @Value("${jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication){
        UsuarioData usuarioData = (UsuarioData) authentication.getPrincipal();
        Date hoje = new Date();
        Date expiracao = new Date(hoje.getTime() + Long.valueOf(expiration));
        return Jwts.builder()
                .setIssuer("Uma aplicacao de teste")
                .setSubject(usuarioData.getEmail())
                .setIssuedAt(hoje)
                .setExpiration(expiracao)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

}
