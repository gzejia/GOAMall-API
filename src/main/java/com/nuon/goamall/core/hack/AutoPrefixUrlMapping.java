package com.nuon.goamall.core.hack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * 自动补充路由前缀。控制器路由修改，实现包名路径截取
 */
public class AutoPrefixUrlMapping extends RequestMappingHandlerMapping {

    @Value("${goamall.api-package}")
    private String apiPackagePath;

    @Override
    protected RequestMappingInfo getMappingForMethod(Method method, Class<?> handlerType) {
        RequestMappingInfo mappingInfo = super.getMappingForMethod(method, handlerType);
        if (mappingInfo != null) {
            String prefix = getPrefix(handlerType);
            /* RequestMappingInfo.paths(prefix).build() 如获取到 "/v1" 的路径
             * .combine(mappingInfo) 表示将"/v1"与具体接口命名合并，如"/v1/test" */
            return RequestMappingInfo.paths(prefix).build().combine(mappingInfo);
        }
        return null;
    }

    private String getPrefix(Class<?> handlerType) {
        String packageName = handlerType.getPackage().getName();
        String dotPath = packageName.replaceAll(this.apiPackagePath, "");
        return dotPath.replace(".", "/");
    }
}
