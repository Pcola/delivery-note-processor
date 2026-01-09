package sk.moonid.processor.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sk.moonid.processor.dto.DeliveryNoteDTO;
import sk.moonid.processor.model.DeliveryNote;
import sk.moonid.processor.repository.DeliveryItemRepository;
import sk.moonid.processor.repository.DeliveryNoteRepository;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@Service
public class DeliveryNoteService {

    private final DeliveryNoteRepository deliveryNoteRepository;

    public DeliveryNoteDTO processUpload(MultipartFile file) {

        DeliveryNote deliveryNote = new DeliveryNote();
        deliveryNote.setFilename(file.getOriginalFilename());
        deliveryNote.setUploadedAt(LocalDate.now());
        deliveryNote.setStatus("UPLOADED");

        DeliveryNote saved = deliveryNoteRepository.save(deliveryNote);

        return new DeliveryNoteDTO(
                saved.getId(),
                saved.getUploadedAt(),
                saved.getFilename(),
                saved.getStatus(),
                List.of()
        );
    }
}
