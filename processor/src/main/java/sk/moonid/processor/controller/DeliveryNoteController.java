package sk.moonid.processor.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sk.moonid.processor.dto.DeliveryNoteDTO;
import sk.moonid.processor.service.DeliveryNoteService;

@AllArgsConstructor
@RestController
@RequestMapping("/api/delivery-notes")
public class DeliveryNoteController {

    private final DeliveryNoteService deliveryNoteService;

    @PostMapping("/upload")
    public ResponseEntity<DeliveryNoteDTO> upload(@RequestParam("file") MultipartFile file) {

        if (file == null || file.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        DeliveryNoteDTO dto = deliveryNoteService.processUpload(file);

        return ResponseEntity.ok(dto);
    }
}
