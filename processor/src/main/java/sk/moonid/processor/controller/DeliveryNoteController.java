package sk.moonid.processor.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sk.moonid.processor.dto.DeliveryNoteDTO;
import sk.moonid.processor.exception.FileUploadException;
import sk.moonid.processor.service.DeliveryNoteService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/delivery-notes")
public class DeliveryNoteController {

    private final DeliveryNoteService deliveryNoteService;

    @PostMapping("/upload")
    public ResponseEntity<DeliveryNoteDTO> upload(@RequestParam("file") MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new FileUploadException("File is empty", "FILE_EMPTY");
        }

        if (file.getSize() > 10_000_000) {
            throw new FileUploadException("File size exceeds 10MB limit", "FILE_TOO_LARGE");
        }


        DeliveryNoteDTO dto = deliveryNoteService.processUpload(file);

        return ResponseEntity.ok(dto);
    }
}
