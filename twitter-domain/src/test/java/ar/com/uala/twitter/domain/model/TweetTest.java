package ar.com.uala.twitter.domain.model;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TweetTest {
    @ParameterizedTest
    @CsvSource(
            {
                    "user1, comentario sobre Java",
                    "user2, comentario sobre Robert Martin",
                    "user1, comentario sobre C#",
                    "user3, comentario sobre Javascript"
            }
    )
    void givenValidParametersWhenConstructorIsCalledThenInstantiateClass(
            final String userId,
            final String text
    ) {
        final Tweet newTweet = new Tweet(
                userId,
                text
        );
        assertNotNull(newTweet);
        assertNotNull(newTweet.id());
        assertNotNull(newTweet.createdOn());
    }

    @ParameterizedTest
    @CsvSource(
            {
                    ",",
                    ", comentario sobre Java",
                    "user2,",
            }
    )
    void givenInvalidParametersWhenConstructorIsCalledThenThrowsException(
            final String userId,
            final String text
    ) {
        assertThrows(
                NullPointerException.class,
                () -> new Tweet(
                        userId,
                        text
                )
        );
    }
}