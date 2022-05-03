package com.ywu.keycloak;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * @author ywu
 * @date 2022/5/3 10:14
 */
public class UserManageHelper {

    private static final OkHttpClient CLIENT = new OkHttpClient();
    private static final Gson gson = new Gson();

    private String apiHost;

    public UserManageHelper(String apiHost) {
        this.apiHost = apiHost;
    }

    public CustomUserInfo getUser(String username) {
        Request request = new Request.Builder()
                .url(String.join("/", apiHost, "/user/", username))
                .build();
        Call call = CLIENT.newCall(request);
        try {
            Response response = call.execute();
            String bodyStr = response.body().string();

            System.out.println("response body string: " + bodyStr);

            if (null == bodyStr || bodyStr.isEmpty()) {
                return null;
            }

            return gson.fromJson(bodyStr, CustomUserInfo.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
