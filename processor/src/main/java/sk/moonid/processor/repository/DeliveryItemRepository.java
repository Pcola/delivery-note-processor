package sk.moonid.processor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.moonid.processor.model.DeliveryItem;

interface DeliveryItemRepository extends JpaRepository<DeliveryItem, Long> {
}
