package sk.moonid.processor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "delivery_items")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_item_seq")
    @SequenceGenerator(name = "delivery_item_seq", sequenceName = "delivery_item_seq", allocationSize = 50)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "delivery_note_id", nullable = false)
    private DeliveryNote deliveryNote;

    @Column(name = "product_name", nullable = false, length = 500)
    private String productName;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "extracted_data", length = 1000)
    private String extractedData;
}
