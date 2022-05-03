package com.ywu.keycloak;

import org.junit.Before;
import org.junit.Test;

/**
 * @author ywu
 * @date 2022/5/3 10:25
 */
public class UserManageHelperTest {

    private UserManageHelper userManageHelper;

    @Before
    public void init() {
        userManageHelper = new UserManageHelper("http://localhost:8089");
    }

    @Test
    public void test() {
        CustomUserInfo userInfo = userManageHelper.getUser("lisi");
        System.out.println(userInfo);
    }
}
