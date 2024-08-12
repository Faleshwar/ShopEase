package com.shopease.security.service;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private final String SECRET_KEY = "sjalka0227w7rwuwsaljkajamzalkfafjl23r827028jfajflkaflau2wroa2882jafsdan";
		
	public String getToken(String username) {
		return Jwts
				.builder()
				.subject(username)
				.issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis()+24*60*60*1000))
				.signWith(getSignedKey())
				.compact();
	}
	
	public String getUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public boolean validateToken(String token, String username) {
		return getUsername(token).equals(username) && !isTokenExpired(token);
	}
	
	private boolean isTokenExpired(String token) {
		Date eDate = extractClaim(token, Claims::getExpiration);
		return eDate.before(new Date());
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts
				.parser()
				.verifyWith(getSignedKey())
				.build()
				.parseSignedClaims(token)
				.getPayload();
	}
	
	private <T> T extractClaim(String token, Function<Claims, T> resolver) {
		Claims claims = extractAllClaims(token);
		return resolver.apply(claims);
	}

	private SecretKey getSignedKey() {
		byte[] keys = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keys);
	}

}
