package ar.com.uala.twitter.application;

import ar.com.uala.twitter.domain.exception.TweetRepositoryException;
import ar.com.uala.twitter.domain.model.Tweet;
import ar.com.uala.twitter.domain.port.secondary.TweetRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class CreateTweetServiceTest {
    @ParameterizedTest
    @CsvSource(
            {
                    "user1, comentario sobre Java",
                    "user2, comentario sobre Robert Martin",
                    "user1, comentario sobre C#",
                    "user3, comentario sobre Javascript"
            }
    )
    void givenValidParametersWhenHandleMethodIsCalledThenReturnNewTweet(
            final String userId,
            final String text
    ) {
        final TweetRepository tweetRepository = Mockito.mock(TweetRepository.class);
        final CreateTweetService target = new CreateTweetService(tweetRepository);
        final Tweet response = target.handle(
                userId,
                text
        );

        assertNotNull(response);
        verify(tweetRepository).insert(any(Tweet.class));
    }

    @ParameterizedTest
    @CsvSource(
            {
                    ", comentario sobre Java",
                    "user1, "
            }
    )
    void givenInvalidParametersWhenHandleMethodIsCalledThenThrowTweetCreationException(
            final String userId,
            final String text
    ) {
        final TweetRepository tweetRepository = Mockito.mock(TweetRepository.class);
        final CreateTweetService target = new CreateTweetService(tweetRepository);

        assertThrows(
                NullPointerException.class,
                () -> target.handle(
                        userId,
                        text
                )
        );
        verify(
                tweetRepository,
                never()
        ).insert(any(Tweet.class));
    }

    @ParameterizedTest
    @CsvSource(
            {
                    "user1, comentario sobre Java",
                    "user2, comentario sobre Robert Martin",
                    "user1, comentario sobre C#",
                    "user3, comentario sobre Javascript"
            }
    )
    void givenValidParametersAndBrokenRepositoryWhenHandleMethodIsCalledThenThrowRepositoryException(
            final String userId,
            final String text
    ) {
        final TweetRepository tweetRepository = Mockito.mock(TweetRepository.class);
        final CreateTweetService target = new CreateTweetService(tweetRepository);

        doThrow(new RuntimeException())
                .when(tweetRepository)
                .insert(any(Tweet.class));

        assertThrows(
                TweetRepositoryException.class,
                () -> target.handle(
                        userId,
                        text
                )
        );
    }
}