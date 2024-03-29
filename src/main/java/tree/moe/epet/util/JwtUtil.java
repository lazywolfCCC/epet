package tree.moe.epet.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import tree.moe.epet.entity.Result;
import tree.moe.epet.exception.TokenException;

import static tree.moe.epet.constant.ResultEnum.*;

import java.util.Date;
import java.util.Map;

public class JwtUtil {
    /**
     * 过期7天
     * */
    private static final long EXPIRE_TIME =  24*60*60 * 1000*7;

    /**
     * jwt密钥
     * */
    private static final String SECRET = "epet";

    /**
     * 生成jwt字符串，五分钟后过期  JWT(json web token)
     * @param userId
     * @param info,Map的value只能存放值的类型为：Map，List，Boolean，Integer，Long，Double，String and Date
     * @return
     * */
    public static String sign(String userId, Map<String, Object> info) {
        try {
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    //将userId保存到token里面
                    .withAudience(userId)
                    //存放自定义数据
                    .withClaim("info", info)
                    
                    .withExpiresAt(date)
                    //token的密钥
                    .sign(algorithm);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 根据token获取userId
     * @param token
     * @return
     * */
    public static String getUserId(String token) {
        try {
            String userId = JWT.decode(token).getAudience().get(0);
            return userId;
        }catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 根据token获取自定义数据info
     * @param token
     * @return
     * */
    public static Map<String, Object> getInfo(String token) {
        try {
            return JWT.decode(token).getClaim("info").asMap();
        }catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 校验token
     * @param token
     * @return
     * */
    public static Result checkSign(String token)throws Exception {
        Result result =  new Result();
        Algorithm algorithm  = Algorithm.HMAC256(SECRET);
        JWTVerifier verifier = JWT.require(algorithm)
           .build();
        if(token == null || "".equals(token))
        {
        	throw new TokenException();
        }
        verifier.verify(token);
        result.setCode(REQUEST_SUCCESS.getCode());
        result.setMsg(REQUEST_SUCCESS.getMsg());
        return result;
        
        	
        
    }
}



