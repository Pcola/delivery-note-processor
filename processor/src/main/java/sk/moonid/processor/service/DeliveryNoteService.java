package sk.moonid.processor.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sk.moonid.processor.dto.DeliveryItemDTO;
import sk.moonid.processor.dto.DeliveryNoteDTO;
import sk.moonid.processor.model.DeliveryNote;
import sk.moonid.processor.repository.DeliveryNoteRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DeliveryNoteService {

    private final DeliveryNoteRepository deliveryNoteRepository;

    public DeliveryNoteDTO processUpload(MultipartFile file) {

        DeliveryNote deliveryNote = new DeliveryNote();
        deliveryNote.setFilename(file.getOriginalFilename());
        deliveryNote.setUploadedAt(LocalDateTime.now());
        deliveryNote.setStatus("PENDING");
        deliveryNote.setItems(new ArrayList<>());

        DeliveryNote saved = deliveryNoteRepository.save(deliveryNote);

        return mapToDTO(saved);
    }

    private DeliveryNoteDTO mapToDTO(DeliveryNote entity) {
        List<DeliveryItemDTO> itemDTOs = entity.getItems().stream()
                .map(item -> new DeliveryItemDTO(
                        item.getProductCode(),
                        item.getProductName(),
                        item.getWarehouse(),
                        item.getQuantity(),
                        item.getUnit()
                ))
                .toList();

        return new DeliveryNoteDTO(
                entity.getDeliveryNumber(),
                entity.getDeliveryDate(),
                entity.getOrderNumber(),
                entity.getSupplier(),
                entity.getSupplierTaxId(),
                entity.getRecipient(),
                entity.getRecipientAddress(),
                entity.getTransportType(),
                entity.getUploadedAt(),
                entity.getFilename(),
                entity.getStatus(),
                itemDTOs
        );
    }
}