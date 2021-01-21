package test;

import java.security.BasicPermission;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.security.auth.AuthPermission;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;

import org.keycloak.representations.idm.authorization.Permission;

import io.quarkus.security.identity.SecurityIdentity;
import io.smallrye.mutiny.Uni;
import io.vertx.core.http.HttpServerRequest;

@Path("/resource")
public class ProtectedResource {

    @Inject
    SecurityIdentity identity;

    @GET
    public String hello() {
        if (identity.checkPermissionBlocking(new ScopePermission("resource", "TEST"))) {
            return "Hello";
        } else {
            throw new WebApplicationException(403);
        }
    }

}
