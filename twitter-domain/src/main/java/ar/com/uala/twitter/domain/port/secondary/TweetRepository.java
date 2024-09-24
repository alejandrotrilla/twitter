package ar.com.uala.twitter.domain.port.secondary;

import ar.com.uala.twitter.domain.model.Tweet;

public interface TweetRepository {
    void insert(final Tweet tweet);
}
