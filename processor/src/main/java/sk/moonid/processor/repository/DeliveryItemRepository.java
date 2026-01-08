package sk.moonid.processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.moonid.processor.model.DeliveryItem;

public interface DeliveryItemRepository extends JpaRepository<DeliveryItem, Long> {
}
