package com.ywu.keycloak.config;

import org.keycloak.adapters.servlet.KeycloakOIDCFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class FilterConfig {

    /**
     * 配置keycloak servlet adapter 过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean<KeycloakOIDCFilter> keycloakOIDCFilter() {
        FilterRegistrationBean<KeycloakOIDCFilter> keycloakOIDCFilter = new FilterRegistrationBean<>();

        keycloakOIDCFilter.setFilter(new KeycloakOIDCFilter());

        // 添加过滤器拦截的URL,即访问这些URL需要认证收授权
        keycloakOIDCFilter.addUrlPatterns("/protected/*");

        // 为过滤器设置keycloak json配置文件的路径
        Map<String, String> initParams = new HashMap<>();
        //String configFileName = "keycloak-bearer-only.json";
        String configFileName = "keycloak-confidential.json";
        String keycloakJsonFilePath = getKeycloakJsonFilePath(configFileName);
        initParams.put(KeycloakOIDCFilter.CONFIG_FILE_PARAM, keycloakJsonFilePath);

        keycloakOIDCFilter.setInitParameters(initParams);

        return keycloakOIDCFilter;
    }

    /**
     * 获取resources目录下keycloak json配置文件的绝对路径
     *
     * @param fileName
     * @return
     */
    private String getKeycloakJsonFilePath(String fileName) {
        URL resource = FilterConfig.class.getResource(String.join(File.separator, "/keycloak", fileName));
        return resource.getPath();
    }
}
