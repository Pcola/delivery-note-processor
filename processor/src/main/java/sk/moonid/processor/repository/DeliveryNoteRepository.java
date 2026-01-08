package sk.moonid.processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.moonid.processor.model.DeliveryNote;

public interface DeliveryNoteRepository extends JpaRepository<DeliveryNote, Long> {
}