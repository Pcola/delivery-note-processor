package sk.moonid.processor.dto;

import java.time.LocalDateTime;

public record ErrorResponse(int status, String error, String message, String errorCode, LocalDateTime timestamp,
                            String path) {
}
