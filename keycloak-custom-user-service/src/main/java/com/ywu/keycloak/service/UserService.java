package com.ywu.keycloak.service;

import com.ywu.keycloak.dto.UserDto;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ywu
 * @date 2022/5/2 19:55
 */
@Service
public class UserService {

    /**
     * 模拟存储用户信息
     */
    private Map<String, String> USER_INFO = new HashMap<>();

    @PostConstruct
    public void init() {
        USER_INFO.put("lisi", "123456aa");
        USER_INFO.put("wangwu", "654321bb");
    }

    /**
     * 根据账号获取用户信息
     * @param id 账户(用户名)
     * @return
     */
    public UserDto getUserById(String id) {
        if (USER_INFO.containsKey(id)) {
            UserDto userDto = new UserDto();
            userDto.setUsername(id);
            userDto.setPassword(USER_INFO.get(id));

            return userDto;
        }

        return null;
    }
}
