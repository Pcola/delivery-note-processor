package sk.moonid.processor.dto;

import lombok.Value;

@Value
public class DeliveryItemDTO {
    Long id;
    String productName;
    Integer quantity;
    String extractedData;
}
