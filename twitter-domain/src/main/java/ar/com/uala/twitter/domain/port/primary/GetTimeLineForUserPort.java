package ar.com.uala.twitter.domain.port.primary;

import ar.com.uala.twitter.domain.model.Page;
import ar.com.uala.twitter.domain.model.Tweet;

public interface GetTimeLineForUserPort {
    Page<Tweet> handle(
            final String userId,
            final int pageSize,
            final int pageIndex
    );
}
