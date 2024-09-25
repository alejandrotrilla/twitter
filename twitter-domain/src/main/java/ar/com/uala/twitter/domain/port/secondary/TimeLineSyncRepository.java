package ar.com.uala.twitter.domain.port.secondary;

import ar.com.uala.twitter.domain.model.Tweet;

public interface TimeLineSyncRepository {
    void publish(
            final String followerUserId,
            final Tweet tweet
    );
}
