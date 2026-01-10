package sk.moonid.processor.exception;

public class FileUploadException extends RuntimeException implements ProcessorException {
    private final String errorCode;

    public FileUploadException(String errorMessage) {
        super(errorMessage);
        this.errorCode = "FILE_UPLOAD_ERROR";
    }

    public FileUploadException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }


    @Override
    public String getErrorCode() {
        return errorCode;
    }
}
