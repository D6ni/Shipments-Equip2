package cat.institutmarianao.shipmentsws.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.lang.String;

import cat.institutmarianao.shipmentsws.model.Address;
import cat.institutmarianao.shipmentsws.model.Shipment;
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
	
	/* JSON */
	@JsonFormat(shape = JsonFormat.Shape.STRING)
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
