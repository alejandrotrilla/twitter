package ar.com.uala.twitter.adapter;

import ar.com.uala.twitter.domain.model.Page;
import ar.com.uala.twitter.domain.model.Tweet;
import ar.com.uala.twitter.domain.port.secondary.TimeLineRepository;
import ar.com.uala.twitter.domain.port.secondary.TimeLineSyncRepository;
import jakarta.inject.Singleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class SynchronizableTimeLineRepositoryInMemoryImpl
        implements TimeLineRepository,
                   TimeLineSyncRepository {
    private final Map<String, List<Tweet>> repository = Collections.synchronizedMap(new HashMap<>());

    @Override
    public Page<Tweet> findAllByPage(
            final String userId,
            final int pageSize,
            final int pageIndex
    ) {
        final List<Tweet> items = repository.getOrDefault(
                userId,
                List.of()
        );
        final List<Tweet> itemsInPage = items.stream()
                                             .skip(pageSize * pageIndex)
                                             .limit(pageSize)
                                             .toList();
        return new Page<>(
                itemsInPage,
                items.size()
        );
    }

    @Override
    public void publish(
            final String followerUserId,
            final Tweet tweet
    ) {
        final List<Tweet> tweets = repository.getOrDefault(
                followerUserId,
                new ArrayList<>()
        );
        tweets.add(tweet);
        repository.put(
                followerUserId,
                tweets
        );
    }
}
