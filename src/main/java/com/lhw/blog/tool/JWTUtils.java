package com.lhw.blog.tool;

import com.lhw.blog.domain.LhwUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * @description: 用于生成和解析JWT
 * @author: lihongwei
 * @time: 2020/4/8 4:01 下午
 */
public class JWTUtils {

    /**
     * 密钥
     */
    private static String secret = "itzhongzi";

    /**
     * 过期时间
     */
    private static long expire = 1000 * 60 * 60 * 24 * 7;

    /**
     * 发行者
     */
    private static String subject = "lihongwei";

    /**
     *  使用 ID 和 pwssword 生成JWTtoken
     * @param user
     * @return
     */
    public static String generateToken(LhwUser user){
        if(user == null || user.getUserId() == null
                || user.getUserNickname().isEmpty()
                || user.getUserTelephoneNumber().isEmpty()
                || user.getUserPassword().isEmpty() ) {
            return null;
        }

        String jwtToken = Jwts.builder().setSubject(subject)
                .claim("id", user.getUserId())
                .claim("password", user.getUserPassword())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expire))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();


        return  jwtToken;
    }

    /**
     * 检验 token
     * @param token
     * @return
     */
    public static Claims checkJWT(String token) {
        try {
            Claims body = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            return body;
        } catch (Exception e) { }

        return null;
    }

}
