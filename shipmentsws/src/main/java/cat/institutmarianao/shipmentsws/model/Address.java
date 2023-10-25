package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/* Lombok */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Lombok */
	@EqualsAndHashCode.Include
	protected Long id;

	private String name;

	private String street;
	private String number;
	private String floor;
	private String door;

	private String city;
	private String province;

	private String postalCode;

	private String country;
}
