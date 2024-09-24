package ar.com.uala.twitter.domain.exception;

public class UserRepositoryException
        extends RuntimeException {
    private final String userId;

    public UserRepositoryException(
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
