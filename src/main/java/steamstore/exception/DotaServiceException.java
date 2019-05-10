package steamstore.exception;

public class DotaServiceException extends RuntimeException {
    public DotaServiceException(Throwable cause) {
        super(cause);
    }

    public DotaServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public DotaServiceException(String message) {
        super(message);
    }
}
