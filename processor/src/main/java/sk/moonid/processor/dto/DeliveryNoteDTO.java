package sk.moonid.processor.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record DeliveryNoteDTO(
        String deliveryNumber,
        LocalDate deliveryDate,
        String orderNumber,
        String supplier,
        String supplierTaxId,
        String recipient,
        String recipientAddress,
        String transportType,
        LocalDateTime uploadedAt,
        String filename,
        String status,
        List<DeliveryItemDTO> items
) {}