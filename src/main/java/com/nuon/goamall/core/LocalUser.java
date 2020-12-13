package com.nuon.goamall.core;

import com.nuon.goamall.exception.ParameterException;
import com.nuon.goamall.model.User;

import java.util.HashMap;
import java.util.Map;

public class LocalUser {

    private static ThreadLocal<Map<String, Object>> threadLocal = new ThreadLocal<>();

    public static void set(User user, Integer scope) {
        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("scope", scope);
        LocalUser.threadLocal.set(map);
    }

    public static void clear() {
        LocalUser.threadLocal.remove();
    }

    public static User getUser() {
        Map<String, Object> map = LocalUser.threadLocal.get();
        if (null == map || null == map.get("user")) {
            throw new ParameterException(20005);
        }
        return (User) map.get("user");
    }

    public static Integer getScope() {
        Map<String, Object> map = LocalUser.threadLocal.get();
        if (null == map || null == map.get("scope")) {
            throw new ParameterException(20005);
        }
        return (Integer) map.get("scope");
    }
}
