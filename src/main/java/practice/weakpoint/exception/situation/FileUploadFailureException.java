package practice.weakpoint.exception.situation;

public class FileUploadFailureException extends RuntimeException{
    public FileUploadFailureException(Throwable cause) {
        super(cause);
    }
}
