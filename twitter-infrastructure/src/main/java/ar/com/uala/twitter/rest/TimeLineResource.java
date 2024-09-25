package ar.com.uala.twitter.rest;

import ar.com.uala.twitter.domain.model.Page;
import ar.com.uala.twitter.domain.model.PageRequest;
import ar.com.uala.twitter.domain.model.Tweet;
import ar.com.uala.twitter.domain.port.secondary.TimeLineRepository;
import ar.com.uala.twitter.rest.dto.TweetDTO;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestPath;
import org.jboss.resteasy.reactive.RestQuery;

import java.util.List;

@Path("time-line/{userId}")
public class TimeLineResource {
    private final TimeLineRepository timeLineRepository;

    @Inject
    public TimeLineResource(final TimeLineRepository timeLineRepository) {
        this.timeLineRepository = timeLineRepository;
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Page<TweetDTO> get(
            @RestPath
            final String userId,
            @RestQuery
            @DefaultValue("10")
            final int pageSize,
            @RestQuery
            @DefaultValue("0")
            final int pageIndex
    ) {
        final Page<Tweet> page = timeLineRepository.findAllByPage(
                userId,
                new PageRequest(
                        pageSize,
                        pageIndex
                )
        );
        final List<TweetDTO> dtos = page.items()
                                        .stream()
                                        .map(TweetDTO::fromModel)
                                        .toList();
        return new Page<>(
                dtos,
                page.totalItems()
        );
    }
}
