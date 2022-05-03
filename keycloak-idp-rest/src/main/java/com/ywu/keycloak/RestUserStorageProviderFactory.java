package com.ywu.keycloak;

import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.provider.ProviderConfigurationBuilder;
import org.keycloak.storage.UserStorageProviderFactory;

import java.util.List;

/**
 * @author ywu
 * @date 2022/5/2 17:43
 */
public class RestUserStorageProviderFactory implements UserStorageProviderFactory<RestUserStorageProvider> {

    public static final String PROVIDER_NAME = "readonly-rest-provider";

    protected static final List<ProviderConfigProperty> configMetadata;

    static {
        configMetadata = ProviderConfigurationBuilder.create()
                .property().name("apiHost")
                .type(ProviderConfigProperty.STRING_TYPE)
                .label("ApiHost")
                .defaultValue("http://localhost:8080")
                .helpText("User info api host")
                .add().build();
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return configMetadata;
    }

    @Override
    public RestUserStorageProvider create(KeycloakSession session, ComponentModel model) {
        String apiHost = model.get("apiHost");
        return new RestUserStorageProvider(session, model, apiHost);
    }

    @Override
    public String getId() {
        return PROVIDER_NAME;
    }
}
