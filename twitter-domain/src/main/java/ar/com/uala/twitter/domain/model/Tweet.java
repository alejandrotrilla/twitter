package ar.com.uala.twitter.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.util.Objects.requireNonNull;

public class Tweet {
    private static final int TEXT_MAX_LONG = 280;
    private static final String TEXT_TOO_LONG_ERROR_MESSAGE =
            "Tweet::Text must not exceed %s characters long.".formatted(TEXT_MAX_LONG);

    private final UUID id;
    private final LocalDateTime createdOn;
    private String userId;
    private String text;

    public Tweet(
            final String userId,
            final String text
    ) {
        this.userId = requireNonNull(
                userId,
                "Tweet::UserId must not be null."
        );
        this.text = requireNonNull(
                text,
                "Tweet::Text must not be null."
        );
        if (this.text.length() > TEXT_MAX_LONG) {
            throw new IllegalArgumentException(TEXT_TOO_LONG_ERROR_MESSAGE);
        }
        this.id = UUID.randomUUID();
        this.createdOn = LocalDateTime.now();
    }

    public UUID id() {
        return id;
    }

    public LocalDateTime createdOn() {
        return createdOn;
    }

    public String userId() {
        return userId;
    }

    public String text() {
        return text;
    }
}
