package com.nuon.goamall.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nuon.goamall.exception.ServiceErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GenericAndJson {

    private static ObjectMapper mapper;

    @Autowired
    public void setMapper(ObjectMapper mapper) {
        GenericAndJson.mapper = mapper;
    }

    /**
     * @param o   对象
     * @param <T> 类型
     * @return 对象转JsonString
     */
    public static <T> String object2Json(T o) {
        try {
            return GenericAndJson.mapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceErrorException(9999);
        }
    }

    /**
     * @param s   JsonString
     * @param tr  转换对象类型
     * @param <T> 转换对象类型
     * @return JsonString 转对象
     */
    public static <T> T json2Object(String s, TypeReference<T> tr) {
        if (null == s) {
            return null;
        }
        try {
            return GenericAndJson.mapper.readValue(s, tr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ServiceErrorException(9999);
        }
    }

//    /**
//     * @param s   JsonString
//     * @param <T> 序列化对象类型
//     * @return JsonString 转对象
//     */
//    public static <T> List<T> json2List(String s) {
//        if (null == s) {
//            return null;
//        }
//        try {
//            return mapper.readValue(s, new TypeReference<List<T>>() {
//            });
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//            throw new ServiceErrorException(9999);
//        }
//    }
}
