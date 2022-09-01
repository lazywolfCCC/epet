package tree.moe.epet.intercepter;


import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import tree.moe.epet.exception.TokenException;
import tree.moe.epet.util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        //从 http 请求头中取出 token
        String token = request.getHeader("token");
        if (token == null) {
            throw new TokenException();
        }

        //验证 token
        JwtUtil.checkSign(token);

        //验证通过后， 这里测试取出JWT中存放的数据
        //获取 token 中的其他数据
        return true;
    }
}

