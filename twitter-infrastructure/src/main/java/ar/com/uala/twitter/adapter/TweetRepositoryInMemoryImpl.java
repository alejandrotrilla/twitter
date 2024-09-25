package ar.com.uala.twitter.adapter;

import ar.com.uala.twitter.domain.model.Tweet;
import ar.com.uala.twitter.domain.port.secondary.TweetRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Singleton
public class TweetRepositoryInMemoryImpl
        implements TweetRepository {
    private final List<Tweet> tweetRepository = Collections.synchronizedList(new ArrayList<>());
    private final TweetProjector tweetProjector;

    @Inject
    public TweetRepositoryInMemoryImpl(final TweetProjector tweetProjector) {
        this.tweetProjector = tweetProjector;
    }

    @Override
    public void insert(final Tweet tweet) {
        tweetRepository.add(tweet);
        tweetProjector.project(tweet);
    }
}
