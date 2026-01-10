package sk.moonid.processor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "delivery_notes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryNote {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_note_seq")
    @SequenceGenerator(name = "delivery_note_seq", sequenceName = "delivery_note_seq", allocationSize = 50)
    private Long id;

    @Column(name = "delivery_number", length = 50)
    private String deliveryNumber;      // Optional - z OCR

    @Column(name = "delivery_date")
    private LocalDate deliveryDate;     // Optional - z OCR

    @Column(name = "order_number", length = 50)
    private String orderNumber;         // Optional

    @Column(name = "supplier", length = 255)
    private String supplier;            // Optional

    @Column(name = "supplier_tax_id", length = 50)
    private String supplierTaxId;       // Optional

    @Column(name = "recipient", length = 255)
    private String recipient;           // Optional

    @Column(name = "recipient_address", length = 500)
    private String recipientAddress;    // Optional

    @Column(name = "transport_type", length = 100)
    private String transportType;       // Optional

    @Column(name = "uploaded_at", nullable = false)
    private LocalDateTime uploadedAt;   // Required - máš ho

    @Column(name = "filename", nullable = false, length = 255)
    private String filename;            // Required - máš ho

    @Column(name = "status", nullable = false, length = 50)
    private String status;              // Required - máš ho

    @OneToMany(mappedBy = "deliveryNote", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeliveryItem> items = new ArrayList<>();
}
