package cat.institutmarianao.shipmentsws.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.lang.String;

import cat.institutmarianao.shipmentsws.model.Address;
import cat.institutmarianao.shipmentsws.model.Shipment;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Lombok */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ShipmentDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Lombok */
	@EqualsAndHashCode.Include
	private Long id;

	private Integer trackingNumber;

	protected String receptionist;

	protected Integer priority;

	
	protected String courier;
	
	protected Date receptionDate = new Date();

	private Shipment.Category category;

	private Address sender;

	private Address recipient;

    private Float weight;

    private Float height;

    private Float width;

    private Float length;

    private Boolean express;

    private Boolean fragile;

    private String note;

}
