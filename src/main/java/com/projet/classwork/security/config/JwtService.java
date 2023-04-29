package com.projet.classwork.security.config;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

	private static final String SECRET_KEY = "2948404D635166546A576E5A7234753778214125442A462D4A614E645267556B";

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);// the subject is either the email or the userName
	}

	public <T> T extractClaim(String token, Function<Claims, T> ClaimsResolver) {
		final Claims claims = extractAllClaims(token);
		return ClaimsResolver.apply(claims);
	}

	//generate token from UserDetails
	public String generateTokenWithUserDetails(UserDetails userDetails) {
		return generateToken(new HashMap<>(), userDetails);
	}

	//generate token using claims
	public String generateToken(
			Map<String, Object> extractClaims,
			UserDetails userDetails) {
		return Jwts
				.builder()
				.setClaims(extractClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis()+1000*60*24))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256)
				.compact();
	}

	//we check if the handed token belongs to the user details in the params
	public boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private Claims extractAllClaims(String token) {
		return Jwts.parserBuilder().
				setSigningKey(getSignInKey()).
				build().
				parseClaimsJws(token).
				getBody();
	}

	private Key getSignInKey() {
		byte[] keyBytes = SECRET_KEY.getBytes();
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
