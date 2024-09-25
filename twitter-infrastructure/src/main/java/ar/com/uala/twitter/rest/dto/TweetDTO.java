package ar.com.uala.twitter.rest.dto;

import ar.com.uala.twitter.domain.model.Tweet;

public record TweetDTO(
        String id,
        String userId,
        String createdOn,
        String text
) {
    public static TweetDTO fromModel(final Tweet tweet) {
        return new TweetDTO(
                tweet.id()
                     .toString(),
                tweet.userId(),
                tweet.createdOn()
                     .toString(),
                tweet.text()
        );
    }
}
