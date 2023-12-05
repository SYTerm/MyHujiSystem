package com.huzd.huji.Utils;

import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.apache.catalina.User;

import java.util.Date;

public class TokenUtils {
    public static String getToken(String userId,String sign) {
         return JWT.create().withAudience(userId) // 将 user id 保存到 token 里面
                .withExpiresAt(DateUtil.offsetHour(new Date(),24)) //24h后token过期
                .sign(Algorithm.HMAC256(sign));// 以 sign 作为 token 的密钥

    }

}
