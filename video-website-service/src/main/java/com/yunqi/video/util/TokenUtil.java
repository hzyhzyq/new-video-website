package com.yunqi.video.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yunqi.video.domain.constant.ErrorInfo;
import com.yunqi.video.domain.exception.ConditionException;

import java.util.Date;

/**
 * @author Tony
 */
public class TokenUtil {

    private static final String ISSUER = "TONY";
    private static final int ExpirationTime = 600000;

    /**
     * 创建一个新的Token,设置它的过期时间为10分钟
     * @param userId
     * @return
     * @throws Exception
     */
    public static String getToken(Integer userId) throws Exception {
        Date now = new Date();
        Date ExpirationDate = new Date(now.getTime()-ExpirationTime);
        //创建JWT
        JWTCreator.Builder builder = JWT.create();
        //配置
        builder.withKeyId(userId.toString());
        builder.withIssuer(ISSUER);
        builder.withExpiresAt(ExpirationDate);
        //加密JWT
        Algorithm algorithm = Algorithm.RSA256(RSAUtil.getPublicKey(),RSAUtil.getPrivateKey());
        return builder.sign(algorithm);
    }

    /**
     * 把Token解密
     * 并判断是否过期或出现其他异常
     * 成功，返回Token的KeyId
     * @param token
     * @return
     */
    public static Integer checkToken(String token){
        try {
            //解密JWT
            Algorithm algorithm = Algorithm.RSA256(RSAUtil.getPublicKey(),RSAUtil.getPrivateKey());
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            //返回KeyId
            return Integer.parseInt(decodedJWT.getKeyId());
        } catch (Exception e) {
            throw new ConditionException(ErrorInfo.TOKEN_EXPIRED);
        }
    }
}
