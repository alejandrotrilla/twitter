package ar.com.uala.twitter.domain.port.primary;

public interface StartFollowingPort {
    void handle(
            final String userId,
            final String userIdToFollow
    );
}
