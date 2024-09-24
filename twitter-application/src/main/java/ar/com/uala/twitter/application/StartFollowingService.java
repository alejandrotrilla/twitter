package ar.com.uala.twitter.application;

import ar.com.uala.twitter.domain.exception.UserRepositoryException;
import ar.com.uala.twitter.domain.port.primary.StartFollowingPort;
import ar.com.uala.twitter.domain.port.secondary.UserRepository;

public class StartFollowingService implements StartFollowingPort {
    private final UserRepository userRepository;

    public StartFollowingService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void handle(
            final String userId,
            final String userIdToFollow
    ) {
        try {
            userRepository.startFollow(userId, userIdToFollow);
        } catch (Exception errorCause) {
            throw new UserRepositoryException(errorCause, userId);
        }
    }
}
