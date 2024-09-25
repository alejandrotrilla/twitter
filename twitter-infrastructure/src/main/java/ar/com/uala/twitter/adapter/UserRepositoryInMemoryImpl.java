package ar.com.uala.twitter.adapter;

import ar.com.uala.twitter.domain.port.secondary.UserRepository;
import jakarta.inject.Singleton;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Singleton
public class UserRepositoryInMemoryImpl
        implements UserRepository {
    private final Map<String, Set<String>> repository = Collections.synchronizedMap(new HashMap<>());
    @Override
    public void startFollow(
            final String userId,
            final String userIdToFollow
    ) {
        final Set<String> followers = getFollowers(userIdToFollow);

        followers.add(userId);
        repository.put(userIdToFollow, followers);
    }

    @Override
    public Set<String> getFollowers(final String userId) {
        return repository.getOrDefault(userId, new HashSet<>());
    }
}
