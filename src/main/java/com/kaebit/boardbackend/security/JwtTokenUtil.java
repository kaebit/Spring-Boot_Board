package com.kaebit.boardbackend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.Serializable;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenUtil implements Serializable {

    private static final long serialVersionUID = 1L;

    private String secretKey = "SeCrEtKeY";

    private byte[] encodedSecretKey = DatatypeConverter.parseBase64Binary(secretKey);

    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS512;

    private final Key KEY = new SecretKeySpec(encodedSecretKey, signatureAlgorithm.getJcaName());

    public String issueToken(String user_id, String uid) {
        Date createdAt = new Date();
        Date dueAt = new Date(createdAt.getTime() + 5000000);

        String token = Jwts.builder().setHeaderParam("typ", "JWT").setSubject(user_id).claim("id", uid).setExpiration(dueAt).signWith(signatureAlgorithm, KEY).compact();

        return token;
    }

    public String getUserIdFromToken(String accessToken) {
        Claims decodedToken = Jwts.parser().setSigningKey(KEY).parseClaimsJws(accessToken).getBody();
        return decodedToken.get("id") + "";
    }
}
