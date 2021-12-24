package com.gabriel.gcscollegeAPI.services;

import java.security.Key;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import com.gabriel.gcscollegeAPI.model.Token;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthToken {
	
	private String SECRET_KEY = "secret";
	
	// creation of token

		private Token createJWT(String id, String subject, String issuer) {
			long nowMillis = System.currentTimeMillis();
			Date now = new Date(nowMillis);
			SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
			byte[] apiKeySecretBytes = SECRET_KEY.getBytes();
			Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
			// Let's set the JWT Claims
			JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(now).setSubject(subject).setIssuer(issuer)
					.signWith(signatureAlgorithm, signingKey);
			// https://github.com/oktadev/okta-java-jwt-example/blob/master/src/main/java/com/okta/createverifytokens/JWTDemo.java
			// Here shows how to add expiration.
			return new Token(builder.compact());
		}

		private Claims verifyToken(String token) {
			Claims claims = Jwts.parser().setSigningKey(SECRET_KEY.getBytes()).parseClaimsJws(token).getBody();
			return claims;
		}

		// check user name and password and return a JWT

}
