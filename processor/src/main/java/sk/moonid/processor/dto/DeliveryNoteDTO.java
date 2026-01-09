package sk.moonid.processor.dto;

import lombok.Value;

import java.time.LocalDate;
import java.util.List;

@Value
public class DeliveryNoteDTO {
    Long id;
    LocalDate uploadedAt;
    String filename;
    String status;
    List<DeliveryItemDTO> items;
}
