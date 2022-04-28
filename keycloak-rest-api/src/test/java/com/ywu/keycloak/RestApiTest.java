package com.ywu.keycloak;

import org.junit.Test;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

public class RestApiTest {

    @Test
    public void listUsers1() {
        // 这里的realm、clientId两个参数太坑了
        // ref: https://www.javaer101.com/article/59254149.html
        Keycloak keycloak = Keycloak.getInstance(
                "http://localhost:8180/auth",
                "master",
                "admin",
                "admin",
                "admin-cli",
                "13777dab-59da-4781-bd6e-bad4813459b9"
        );

        List<UserRepresentation> users = keycloak.realm("SpringBoot")
                .users()
                .list();

        System.out.println(users);
        for (UserRepresentation user : users) {
            System.out.println(user.getUsername());
        }
    }

    @Test
    public void listUsers2() {
        Keycloak keycloak = Keycloak.getInstance(
                "http://localhost:8180/auth",
                "master",
                "admin",
                "admin",
                "admin-cli"
        );

        List<UserRepresentation> users = keycloak.realm("SpringBoot")
                .users()
                .list();

        System.out.println(users);
        for (UserRepresentation user : users) {
            System.out.println(user.getUsername());
        }
    }

    @Test
    public void listUsers3() {
        Keycloak keycloak = Keycloak.getInstance(
                "http://localhost:8180/auth",
                "master",
                "SpringBoot-realm",
                "eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICI4Wm9XTzM0MUdNTXE5WGV2bFhCbTdTLUg4RDViOTQ4ZDA4cWxjdXhKV3k0In0.eyJleHAiOjE2NTExNTMwMzgsImlhdCI6MTY1MTE1MjQzOCwianRpIjoiZTE1YzM0OWItZDkwMS00YTNmLWIxMmItOGFhNjIzOTk3YTBkIiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MTgwL2F1dGgvcmVhbG1zL21hc3RlciIsImF1ZCI6WyJzZWN1cml0eS1hZG1pbi1jb25zb2xlIiwibWFzdGVyLXJlYWxtIiwiYWNjb3VudCJdLCJzdWIiOiI2MmU2YTBlNy1mMzI3LTRmMjktODMzYi1iMjM3MWYxYjhmMDgiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJTcHJpbmdCb290LXJlYWxtIiwic2Vzc2lvbl9zdGF0ZSI6IjY4MDgzOWZlLWNhNzYtNGMxNS05NGNiLTJhZDYxMmMwYTcxMyIsImFjciI6IjEiLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiY3JlYXRlLXJlYWxtIiwib2ZmbGluZV9hY2Nlc3MiLCJhZG1pbiIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsiU3ByaW5nQm9vdC1yZWFsbSI6eyJyb2xlcyI6WyJ2aWV3LXJlYWxtIiwidmlldy1pZGVudGl0eS1wcm92aWRlcnMiLCJtYW5hZ2UtaWRlbnRpdHktcHJvdmlkZXJzIiwiaW1wZXJzb25hdGlvbiIsImNyZWF0ZS1jbGllbnQiLCJtYW5hZ2UtdXNlcnMiLCJxdWVyeS1yZWFsbXMiLCJ2aWV3LWF1dGhvcml6YXRpb24iLCJxdWVyeS1jbGllbnRzIiwicXVlcnktdXNlcnMiLCJtYW5hZ2UtZXZlbnRzIiwibWFuYWdlLXJlYWxtIiwidmlldy1ldmVudHMiLCJ2aWV3LXVzZXJzIiwidmlldy1jbGllbnRzIiwibWFuYWdlLWF1dGhvcml6YXRpb24iLCJtYW5hZ2UtY2xpZW50cyIsInF1ZXJ5LWdyb3VwcyJdfSwibWFzdGVyLXJlYWxtIjp7InJvbGVzIjpbInZpZXctcmVhbG0iLCJ2aWV3LWlkZW50aXR5LXByb3ZpZGVycyIsIm1hbmFnZS1pZGVudGl0eS1wcm92aWRlcnMiLCJpbXBlcnNvbmF0aW9uIiwiY3JlYXRlLWNsaWVudCIsIm1hbmFnZS11c2VycyIsInF1ZXJ5LXJlYWxtcyIsInZpZXctYXV0aG9yaXphdGlvbiIsInF1ZXJ5LWNsaWVudHMiLCJxdWVyeS11c2VycyIsIm1hbmFnZS1ldmVudHMiLCJtYW5hZ2UtcmVhbG0iLCJ2aWV3LWV2ZW50cyIsInZpZXctdXNlcnMiLCJ2aWV3LWNsaWVudHMiLCJtYW5hZ2UtYXV0aG9yaXphdGlvbiIsIm1hbmFnZS1jbGllbnRzIiwicXVlcnktZ3JvdXBzIl19LCJhY2NvdW50Ijp7InJvbGVzIjpbIm1hbmFnZS1hY2NvdW50IiwibWFuYWdlLWFjY291bnQtbGlua3MiLCJ2aWV3LXByb2ZpbGUiXX19LCJzY29wZSI6InByb2ZpbGUgZW1haWwiLCJjbGllbnRJZCI6IlNwcmluZ0Jvb3QtcmVhbG0iLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImNsaWVudEhvc3QiOiIxNzIuMjEuMC4xIiwicHJlZmVycmVkX3VzZXJuYW1lIjoic2VydmljZS1hY2NvdW50LXNwcmluZ2Jvb3QtcmVhbG0iLCJjbGllbnRBZGRyZXNzIjoiMTcyLjIxLjAuMSJ9.rrS3b6K0W6kvIc-MrIMd-Y3H7PWr732BTDIavLx0gqAfjZ_pr46o_IAuPbDzN8lwWEjiWXeWlAE6YvTiEve_X0o7ByrqhzcU1CSEsHbk_yOqNsJ58cSaEQ8yLoPvr4ME4bwYXGjUQfn4CjgwvKMlWZKbfTa4LUCIsZmW6_azxgx1t7-48w0_I0n_3JLbyQQKN-Ji0WzEpgJt5fK89zgU4uZGOLHOIRjAxBaG8DxiDlkramN94scZ9GYTh636mepFNc56BIG1xsWOUsaU_slexx7Wd6jUsr2T_jTsRjp5bRErLhHIw2cXZkb-RXMjTbhaQbIaQMiJkU-RZjRWEjgpNQ"
        );

        List<UserRepresentation> users = keycloak.realm("SpringBoot")
                .users()
                .list();

        System.out.println(users);
        for (UserRepresentation user : users) {
            System.out.println(user.getUsername());
        }
    }

    @Test
    public void listUsers4() {
        Keycloak keycloak = Keycloak.getInstance(
                "http://localhost:8180/auth",
                "master",
                "admin",
                "admin",
                "SpringBoot-realm"
        );

        List<UserRepresentation> users = keycloak.realm("SpringBoot")
                .users()
                .list();

        System.out.println(users);
        for (UserRepresentation user : users) {
            System.out.println(user.getUsername());
        }
    }
}
