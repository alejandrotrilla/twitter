package ar.com.uala.twitter.domain.port.primary;

import ar.com.uala.twitter.domain.model.Tweet;

public interface CreateTweetPort {
    Tweet handle(final String userId, final String text);
}
