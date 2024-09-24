package ar.com.uala.twitter.domain.port.secondary;

public interface UserRepository {
    void startFollow(final String userId, final String userIdToFollow);
}
