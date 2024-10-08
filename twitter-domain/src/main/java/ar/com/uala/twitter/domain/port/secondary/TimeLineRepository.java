package ar.com.uala.twitter.domain.port.secondary;

import ar.com.uala.twitter.domain.model.Page;
import ar.com.uala.twitter.domain.model.Tweet;

public interface TimeLineRepository {
    Page<Tweet> findAllByPage(
            final String userId,
            final int pageSize,
            final int pageIndex
    );
}
