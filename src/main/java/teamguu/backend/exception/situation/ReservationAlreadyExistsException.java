package teamguu.backend.exception.situation;

public class ReservationAlreadyExistsException extends RuntimeException {
    public ReservationAlreadyExistsException(String message) {
        super(message);
    }
}
