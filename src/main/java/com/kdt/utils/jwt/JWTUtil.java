package com.kdt.utils.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JWTUtil {

    private static long expire = 60 * 5; // 过期时间（秒）

    private static String secret = "sdfunkgyqundixuaoemgishgytqpalds";

    public static String generateJWT(String userName) {
        Date now = new Date();
        Date expireTime = new Date(now.getTime() + 1000 * expire);
        String jwt = Jwts.builder()
                .setHeaderParam("type", "JWT")
                .setSubject(userName)
                .setIssuedAt(now)
                .setExpiration(expireTime)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
        return jwt;
    }

    public static Claims getClaimsByJWT(String jwt) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static boolean invalidateJWT(String jwt) {
        JwtParser jwtParser = Jwts.parser().setSigningKey(jwt).requireExpiration(new Date());
        return true;
    }

}
