package teamguu.backend.exception.situation;

public class TeamNameAlreadyExistsException extends RuntimeException{
    public TeamNameAlreadyExistsException(String message) {
        super(message);
    }
}
