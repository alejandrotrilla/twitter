package ar.com.uala.twitter.rest;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestPath;

@Path("/time-line/{userId}")
public class TimeLineResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String get(
            @RestPath
            final String userId
    ) {
        return userId;
    }
}
