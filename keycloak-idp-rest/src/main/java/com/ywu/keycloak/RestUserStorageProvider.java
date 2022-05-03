package com.ywu.keycloak;

import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;
import org.keycloak.models.credential.PasswordCredentialModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.adapter.AbstractUserAdapter;
import org.keycloak.storage.user.UserLookupProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ywu
 * @date 2022/5/2 12:57
 */
public class RestUserStorageProvider implements UserLookupProvider, UserStorageProvider, CredentialInputValidator {
    /**
     * 缓存加载的用户
     */
    protected Map<String, CustomUserInfo> loadedUsers = new HashMap<>();

    protected KeycloakSession session;
    protected ComponentModel model;

    private UserManageHelper userManageHelper;

    public RestUserStorageProvider(KeycloakSession session, ComponentModel model, String apiHost) {
        this.session = session;
        this.model = model;

        this.userManageHelper = new UserManageHelper(apiHost);
    }

    @Override
    public UserModel getUserById(String id, RealmModel realmModel) {
        StorageId storageId = new StorageId(id);
        String username = storageId.getExternalId();
        return getUserByUsername(username, realmModel);
    }

    @Override
    public UserModel getUserByUsername(String username, RealmModel realmModel) {
        System.out.println("get user: " + username);

        UserModel adapter = null;

        CustomUserInfo userInfo = getUserInfo(username);

        if (null == userInfo) {
            return null;
        }

        return createAdapter(realmModel, username);
    }

    @Override
    public UserModel getUserByEmail(String email, RealmModel realmModel) {
        return null;
    }

    protected UserModel createAdapter(RealmModel realm, String username) {
        UserModel userModel = new AbstractUserAdapter(session, realm, model) {
            @Override
            public String getUsername() {
                return username;
            }
        };

        return userModel;
    }

    @Override
    public void close() {
        System.out.println("RestUserStorageProvider close");
    }

    @Override
    public boolean supportsCredentialType(String credentialType) {
        System.out.println("input credential Type: " + credentialType);
        return credentialType.equals(PasswordCredentialModel.TYPE);
    }

    @Override
    public boolean isConfiguredFor(RealmModel realm, UserModel user, String credentialType) {
        CustomUserInfo userInfo = getUserInfo(user.getUsername());
        return credentialType.equals(PasswordCredentialModel.TYPE) && null != userInfo && userInfo.getPassword() != null;
    }

    @Override
    public boolean isValid(RealmModel realm, UserModel user, CredentialInput credentialInput) {
        if (!supportsCredentialType(credentialInput.getType())) {
            return false;
        }

        CustomUserInfo userInfo = getUserInfo(user.getUsername());
        if (null == userInfo || null == userInfo.getPassword()) {
            return false;
        }
        return userInfo.getPassword().equals(credentialInput.getChallengeResponse());
    }

    private CustomUserInfo getUserInfo(String username) {
        CustomUserInfo customUserInfo = loadedUsers.get(username);
        if (null == customUserInfo) {
            customUserInfo = userManageHelper.getUser(username);
            if (null != customUserInfo) {
                loadedUsers.put(username, customUserInfo);
            }
        }
        return customUserInfo;
    }
}
