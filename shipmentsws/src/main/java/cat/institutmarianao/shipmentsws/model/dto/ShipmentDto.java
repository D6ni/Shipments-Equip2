package cat.institutmarianao.shipmentsws.model.dto;

import java.io.Serializable;
import java.util.Date;

import cat.institutmarianao.shipmentsws.model.Address;
import cat.institutmarianao.shipmentsws.model.Shipment;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Lombok */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
/* JPA*/
@Entity
@Table(name = "shipments")
public class ShipmentDto implements Serializable {

	private static final long serialVersionUID = 1L;
	/*JPA*/
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	/* Lombok */
	@EqualsAndHashCode.Include
	private Long id;

	@Column(name = "tracking_number")
	private Integer trackingNumber;

	protected String receptionist;

	@Column(name = "priority")
	protected Integer priority;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courier_username", referencedColumnName = "username")
	protected String courier;
	
	@Column(name = "reception_date")
	protected Date receptionDate = new Date();

	@Enumerated(EnumType.STRING)
    @Column(name = "category")
	private Shipment.Category category;

	@Embedded
	private Address sender;

	@Embedded
	private Address recipient;

    @Column(name = "weight")
    private Float weight;

    @Column(name = "height")
    private Float height;

    @Column(name = "width")
    private Float width;

    @Column(name = "length")
    private Float length;

    @Column(name = "express")
    private Boolean express;

    @Column(name = "fragile")
    private Boolean fragile;

    @Column(name = "note")
    private String note;

}
