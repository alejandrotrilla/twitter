package ar.com.uala.twitter.domain.model;

public record PageRequest(
        int pageSize,
        int pageIndex
) {
    public PageRequest {
        if (pageSize <= 0) {
            throw new IllegalArgumentException("PageSize must not be negative nor zero.");
        }
        if (pageIndex < 0) {
            throw new IllegalArgumentException("PageIndex must not be negative.");
        }
    }
}
