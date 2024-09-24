package ar.com.uala.twitter.domain.exception;

import ar.com.uala.twitter.domain.model.Tweet;

public class TweetRepositoryException
        extends RuntimeException {
    private final Tweet newTweet;

    public TweetRepositoryException(
            final Throwable cause,
            final Tweet newTweet
    ) {
        super(cause);
        this.newTweet = newTweet;
    }

    public Tweet newTweet() {
        return newTweet;
    }
}
