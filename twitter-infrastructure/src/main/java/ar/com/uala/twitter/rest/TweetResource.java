package ar.com.uala.twitter.rest;

import ar.com.uala.twitter.application.CreateTweetService;
import ar.com.uala.twitter.domain.port.secondary.TweetRepository;
import ar.com.uala.twitter.rest.dto.CreateTweetRequestDTO;
import ar.com.uala.twitter.rest.dto.TweetDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.RestPath;

@Path("tweet/{userId}")
public class TweetResource {
    private final CreateTweetService createTweetService;

    @Inject
    public TweetResource(final TweetRepository tweetRepository) {
        this.createTweetService = new CreateTweetService(tweetRepository);
    }

    @POST
    public TweetDTO create(
            @RestPath
            final String userId,
            final CreateTweetRequestDTO request
    ) {
        return TweetDTO.fromModel(createTweetService.handle(userId, request.text()));
    }
}
