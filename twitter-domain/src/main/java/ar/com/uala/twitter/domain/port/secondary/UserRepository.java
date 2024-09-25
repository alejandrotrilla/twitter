package ar.com.uala.twitter.domain.port.secondary;

import java.util.Set;

public interface UserRepository {
    void startFollow(final String userId, final String userIdToFollow);

    Set<String> getFollowers(final String userId);
}
