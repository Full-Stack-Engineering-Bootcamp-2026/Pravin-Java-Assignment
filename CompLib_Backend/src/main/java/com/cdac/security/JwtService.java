
package com.cdac.security;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private long expiration;
    // password encoder chahiye
    //

    public String generateToken(UserDetails userDetails) {

        return generateToken(new HashMap<>(), userDetails);

    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {

        SecretKey key = getSigningKey();

        Instant now = Instant.now();

        JWTClaimsSet claims = new JWTClaimsSet.Builder()
        .subject(userDetails.getUsername())
        .issueTime(Date.from(now))
        .expirationTime(Date.from(now.plusMillis(expiration)))
        .build();
      
        extraClaims.forEach((k, v) -> {
            try {
                claims.toJSONObject().put(k, v);
            } catch (Exception e) {
            }

        });
        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader(JWSAlgorithm.HS256),
                claims);
        try {
            signedJWT.sign(new MACSigner(key.getEncoded()));
            return signedJWT.serialize(); // return token string
        } catch (JOSEException e) {
            e.printStackTrace();
            throw new RuntimeException("Error signing JWT", e);
        }
    }

    public JWTClaimsSet extractAllClaims(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(getSigningKey().getEncoded());

            if (!signedJWT.verify(verifier)) {
                throw new RuntimeException("Invalid JWT signature");
            }

            return signedJWT.getJWTClaimsSet();

        } catch (Exception e) {
            throw new RuntimeException("Invalid JWT token", e);
        }
    }

    public String extractUsername(String token) {
        try {
            return extractAllClaims(token).getSubject();
        } catch (Exception e) {
            throw new RuntimeException("Cannot extract username", e);
        }
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        try {
            String username = extractUsername(token);
            JWTClaimsSet claims = extractAllClaims(token);
            Date expiry = claims.getExpirationTime();

            return username.equals(userDetails.getUsername())
                    && expiry.after(new Date()); // not expired

        } catch (Exception e) {
            return false;
        }
    }

    private SecretKey getSigningKey() {

        byte[] key = new byte[32];
        new SecureRandom().nextBytes(key);
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        return new SecretKeySpec(keyBytes, "HmacSHA256");
    }

}