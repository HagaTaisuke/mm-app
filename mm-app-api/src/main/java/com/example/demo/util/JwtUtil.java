package com.example.demo.util;

import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtUtil {
	private static final String SECRET_KEY_BASE64 = "MO5Qo0j4RhVNyGpbQ+JOBibW2+XSMD0vKQ8Nux02Oe3o43aJe0SFJTtzjoku/f1a0tY+Nx2HzOfBNigHfLLgFg==";
	private static final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

	private static SecretKey getSecretKey() {
		byte[] decodedKey = java.util.Base64.getDecoder().decode(SECRET_KEY_BASE64);
		return Keys.hmacShaKeyFor(decodedKey);
	}

	public static String generateToken(String username) {
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(getSecretKey(), SignatureAlgorithm.HS512)
				.compact();
	}

	public static String getUsernameFromToken(String token) {
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(getSecretKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
		return claims.getSubject();
	}

	public static boolean validateToken(String token, String username) {
		String tokenUsername = getUsernameFromToken(token);
		return (username.equals(tokenUsername) && !isTokenExpired(token));
	}

	private static boolean isTokenExpired(String token) {
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(getSecretKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
		Date expiration = claims.getExpiration();
		return expiration.before(new Date());
	}
}
