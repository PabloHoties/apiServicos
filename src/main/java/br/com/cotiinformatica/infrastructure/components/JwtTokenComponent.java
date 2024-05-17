package br.com.cotiinformatica.infrastructure.components;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenComponent {

	@Value("${jwt.secret}")
	private String jwtSecret;
	
	public String generateToken(String email) throws Exception {
		
		return Jwts.builder()
				.setSubject(email)
				.setIssuedAt(new Date())
				.signWith(SignatureAlgorithm.HS256, jwtSecret)
				.compact();
	}
}
