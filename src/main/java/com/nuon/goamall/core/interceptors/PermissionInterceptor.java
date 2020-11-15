package com.nuon.goamall.core.interceptors;

import com.auth0.jwt.interfaces.Claim;
import com.nuon.goamall.exception.ForbiddenException;
import com.nuon.goamall.exception.NoAuthorizationException;
import com.nuon.goamall.model.User;
import com.nuon.goamall.util.JwtToken;
import jdk.nashorn.internal.runtime.options.Option;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

public class PermissionInterceptor extends HandlerInterceptorAdapter {

    public PermissionInterceptor() {
        super();
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Optional<ScopeLevel> scopeLevel = this.getScopeLevel(handler);
        if (!scopeLevel.isPresent()) {
            return true;
        }

        String token = this.getToken(request);
        Optional<Map<String, Claim>> optionalMap = JwtToken.getClaims(token);
        Map<String, Claim> map = optionalMap.orElseThrow(
                () -> new NoAuthorizationException(10004));

        boolean valid = this.hasPermission(scopeLevel.get(), map);
        return valid;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }

    public boolean hasPermission(ScopeLevel scopeLevel, Map<String, Claim> map) {
        Integer level = scopeLevel.value();
        Integer scope = map.get("scope").asInt();
        if (level > scope) {
            throw new ForbiddenException(10005);
        }
        return true;
    }

    public String getToken(HttpServletRequest request) {
        String barerToken = request.getHeader("Authorization");
        if (StringUtils.isEmpty(barerToken) || !barerToken.startsWith("Bearer")) {
            throw new NoAuthorizationException(10004);
        }

        String[] tokens = barerToken.split(" ");
        if (!(tokens.length == 2)) {
            throw new NoAuthorizationException(10004);
        }
        return tokens[1];
    }

    public Optional<ScopeLevel> getScopeLevel(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            ScopeLevel scopeLevel = handlerMethod.getMethod().getAnnotation(ScopeLevel.class);
            if (scopeLevel != null) {
                return Optional.of(scopeLevel);
            }
        }
        return Optional.empty();
    }
}
