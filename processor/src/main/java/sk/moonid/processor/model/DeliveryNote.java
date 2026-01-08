package sk.moonid.processor.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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

    @Column(name = "uploaded_at", nullable = false)
    private LocalDate uploadedAt;

    @Column(name = "filename", nullable = false, length = 255)
    private String filename;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @OneToMany(mappedBy = "deliveryNote", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DeliveryItem> items;
}
