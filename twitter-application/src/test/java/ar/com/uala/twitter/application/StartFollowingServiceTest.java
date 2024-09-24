package ar.com.uala.twitter.application;

import ar.com.uala.twitter.domain.exception.UserRepositoryException;
import ar.com.uala.twitter.domain.port.secondary.UserRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;

class StartFollowingServiceTest {
    @ParameterizedTest
    @CsvSource(
            {
                    "user1, user2",
                    "user1, user3",
                    "user1, user4",
                    "user2, user3",
                    "user2, user4",
            }
    )
    void givenValidParametersWhenHandleMethodIsCalledThenUpdateUserFollowing(
            final String userId,
            final String userIdToFollow
    ) {
        final UserRepository userRepository = Mockito.mock(UserRepository.class);
        final StartFollowingService target = new StartFollowingService(userRepository);

        target.handle(
                userId,
                userIdToFollow
        );

        verify(userRepository)
                .startFollow(
                        userId,
                        userIdToFollow
                );
    }

    @ParameterizedTest
    @CsvSource(
            {
                    "user1, user2",
                    "user1, user3",
                    "user1, user4",
                    "user2, user3",
                    "user2, user4",
            }
    )
    void givenValidParametersAndBrokenRepositoryWhenHandleMethodIsCalledThenThrowUserRepositoryException(
            final String userId,
            final String userIdToFollow
    ) {
        final UserRepository userRepository = Mockito.mock(UserRepository.class);
        final StartFollowingService target = new StartFollowingService(userRepository);

        doThrow(new RuntimeException())
                .when(userRepository)
                .startFollow(
                        anyString(),
                        anyString()
                );

        assertThrows(
                UserRepositoryException.class,
                () -> target.handle(
                        userId,
                        userIdToFollow
                )
        );
    }
}