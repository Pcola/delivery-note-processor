package sk.moonid.processor.exception;

public class DeliveryNoteException extends RuntimeException implements ProcessorException {
    private final String errorCode;

    public DeliveryNoteException(String errorMessage) {
        super(errorMessage);
        this.errorCode = "DELIVERY_NOTE_ERROR";
    }

    public DeliveryNoteException(String errorMessage, String errorCode) {
        super(errorMessage);
        this.errorCode = errorCode;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }
}
