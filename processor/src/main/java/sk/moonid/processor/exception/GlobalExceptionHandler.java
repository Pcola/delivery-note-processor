package sk.moonid.processor.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import sk.moonid.processor.dto.ErrorResponse;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private ResponseEntity<ErrorResponse> buildErrorResponse(
            Exception ex,
            WebRequest request,
            int status,
            String statusText) {

        log.error("{}: {}", statusText, ex.getMessage(), ex);

        String errorCode = "INTERNAL_ERROR";
        if (ex instanceof ProcessorException) {
            errorCode = ((ProcessorException) ex).getErrorCode();
        }

        ErrorResponse response = new ErrorResponse(
                status,
                statusText,
                ex.getMessage(),
                errorCode,
                LocalDateTime.now(),
                request.getDescription(false).replace("uri=", "")
        );

        return ResponseEntity.status(status).body(response);
    }

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<ErrorResponse> handleFileUploadException(FileUploadException ex, WebRequest request) {
        return buildErrorResponse(ex, request, 400, "Bad Request");
    }

    @ExceptionHandler(DeliveryNoteException.class)
    public ResponseEntity<ErrorResponse> handleDeliveryNoteException(DeliveryNoteException ex, WebRequest request) {
        return buildErrorResponse(ex, request, 500, "Internal Server Error");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex, WebRequest request) {
        return buildErrorResponse(ex, request, 500, "Internal Server Error");
    }
}
