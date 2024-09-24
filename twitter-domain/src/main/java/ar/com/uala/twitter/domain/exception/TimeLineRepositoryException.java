package ar.com.uala.twitter.domain.exception;

public class TimeLineRepositoryException
        extends RuntimeException {
    private final String userId;

    public TimeLineRepositoryException(
            final Throwable cause,
            final String userId
    ) {
        super(cause);
        this.userId = userId;
    }

    public String userId() {
        return userId;
    }
}
