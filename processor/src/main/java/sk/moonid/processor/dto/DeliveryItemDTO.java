package sk.moonid.processor.dto;

public record DeliveryItemDTO(
        String productCode,
        String productName,
        String warehouse,
        Integer quantity,
        String unit
) {}