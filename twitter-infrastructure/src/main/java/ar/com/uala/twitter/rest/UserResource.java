package ar.com.uala.twitter.rest;

import ar.com.uala.twitter.domain.port.secondary.UserRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestPath;

@Path("user/{userId}")
public class UserResource {
    private final UserRepository userRepository;

    @Inject
    public UserResource(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PUT
    @Path("start-following/{userIdToFollow}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public void startFollowing(
            @RestPath
            final String userId,
            @RestPath
            final String userIdToFollow
    ) {
        userRepository.startFollow(userId, userIdToFollow);
    }
}
