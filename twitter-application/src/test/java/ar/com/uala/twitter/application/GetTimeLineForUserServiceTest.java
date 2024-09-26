package ar.com.uala.twitter.application;

import ar.com.uala.twitter.domain.exception.TimeLineRepositoryException;
import ar.com.uala.twitter.domain.model.Page;
import ar.com.uala.twitter.domain.model.Tweet;
import ar.com.uala.twitter.domain.port.secondary.TimeLineRepository;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

class GetTimeLineForUserServiceTest {
    @ParameterizedTest
    @CsvSource(
            {
                    "user1, 10, 0",
                    "user1, 10, 1",
                    "user1, 1, 0",
                    "user1, 1, 1",
            }
    )
    void givenValidParametersWhenHandleMethodIsCalledThenReturnTimeLine(
            final String userId,
            final int pageSize,
            final int pageIndex
    ) {
        final TimeLineRepository timeLineRepository = Mockito.mock(TimeLineRepository.class);
        final GetTimeLineForUserService target = new GetTimeLineForUserService(timeLineRepository);
        final Page<Tweet> page = new Page<>(
                List.of(),
                0
        );

        when(timeLineRepository.findAllByPage(
                anyString(),
                anyInt(),
                anyInt()
        )).thenReturn(page);

        final Page<Tweet> result = target.handle(
                userId,
                pageSize,
                pageIndex
        );

        assertNotNull(result);
        assertEquals(
                page,
                result
        );
    }

    @ParameterizedTest
    @CsvSource(
            {
                    "user1, 10, 0",
                    "user1, 10, 1",
                    "user1, 1, 0",
                    "user1, 1, 1",
            }
    )
    void givenValidParametersAndBrokenRepositoryWhenHandleMethodIsCalledThenThrowTimeLineRepositoryException(
            final String userId,
            final int pageSize,
            final int pageIndex
    ) {
        final TimeLineRepository timeLineRepository = Mockito.mock(TimeLineRepository.class);
        final GetTimeLineForUserService target = new GetTimeLineForUserService(timeLineRepository);

        doThrow(new RuntimeException())
                .when(timeLineRepository)
                .findAllByPage(
                        anyString(),
                        anyInt(),
                        anyInt()
                );

        assertThrows(
                TimeLineRepositoryException.class,
                () -> target.handle(
                        userId,
                        pageSize,
                        pageIndex
                )
        );
    }
}