package ar.com.uala.twitter.adapter;

import ar.com.uala.twitter.domain.model.Tweet;
import ar.com.uala.twitter.domain.port.secondary.TimeLineSyncRepository;
import ar.com.uala.twitter.domain.port.secondary.UserRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
public class TweetProjector {
    private final TimeLineSyncRepository timeLineRepository;
    private final UserRepository userRepository;

    @Inject
    public TweetProjector(
            final TimeLineSyncRepository timeLineSyncRepository,
            final UserRepository userRepository
    ) {
        this.timeLineRepository = timeLineSyncRepository;
        this.userRepository = userRepository;
    }

    public void project(final Tweet tweet) {
        userRepository.getFollowers(tweet.userId())
                      .forEach(followerUserId -> timeLineRepository.publish(
                                       followerUserId,
                                       tweet
                               )
                      );
    }
}
