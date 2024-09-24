package ar.com.uala.twitter.application;

import ar.com.uala.twitter.domain.exception.TweetRepositoryException;
import ar.com.uala.twitter.domain.model.Tweet;
import ar.com.uala.twitter.domain.port.primary.CreateTweetPort;
import ar.com.uala.twitter.domain.port.secondary.TweetRepository;

public class CreateTweetService
        implements CreateTweetPort {
    private final TweetRepository tweetRepository;

    public CreateTweetService(final TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
    public Tweet handle(
            final String userId,
            final String text
    ) {
        final Tweet newTweet = new Tweet(
                userId,
                text
        );
        insert(newTweet);
        return newTweet;
    }

    private void insert(final Tweet newTweet) {
        try  {
            tweetRepository.insert(newTweet);
        } catch (Exception errorCause) {
            throw new TweetRepositoryException(errorCause, newTweet);
        }
    }
}
