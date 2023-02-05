package com.qiuxinyu.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.HashMap;

public class JWTUtils {
    public static final String JWT_SECRET = "!34ADAS";

    public static final long EXPIRE = 60 * 60 * 1000;

    public static String generateToken(String userId) {
        return JWT.create()
                .withHeader(new HashMap<>())  // Header
                .withClaim("userId", userId)  // Payload
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE))  // 过期时间
                .sign(Algorithm.HMAC256(JWT_SECRET));  // 签名用的secret
    }

    public static String resolveToken(String token){
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(JWT_SECRET)).build();
        return jwtVerifier.verify(token).getClaim("userId").asString();
    }
}
