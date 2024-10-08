package ar.com.uala.twitter.application;

import ar.com.uala.twitter.domain.exception.TimeLineRepositoryException;
import ar.com.uala.twitter.domain.model.Page;
import ar.com.uala.twitter.domain.model.Tweet;
import ar.com.uala.twitter.domain.port.primary.GetTimeLineForUserPort;
import ar.com.uala.twitter.domain.port.secondary.TimeLineRepository;

public class GetTimeLineForUserService
        implements GetTimeLineForUserPort {
    private final TimeLineRepository timeLineRepository;

    public GetTimeLineForUserService(final TimeLineRepository timeLineRepository) {
        this.timeLineRepository = timeLineRepository;
    }

    @Override
    public Page<Tweet> handle(
            final String userId,
            final int pageSize,
            final int pageIndex
    ) {
        try {
            return timeLineRepository.findAllByPage(
                    userId,
                    pageSize,
                    pageIndex
            );
        } catch (Exception errorCause) {
            throw new TimeLineRepositoryException(errorCause, userId);
        }
    }
}
