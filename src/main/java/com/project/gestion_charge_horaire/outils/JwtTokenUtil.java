package com.project.gestion_charge_horaire.outils;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtTokenUtil {
//
//    public JwtTokenUtil() {
//
//    }
//
//    public String createToken(String email, String password , List<String> roles) {
//        Claims claims = (Claims) Jwts.claims().add(email,password);
//        claims.put("roles", roles);
//
//        return Jwts.builder()
//                .setClaims(claims)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
//                .signWith(SignatureAlgorithm.HS512, secret)
//                .compact();
//    }
}
