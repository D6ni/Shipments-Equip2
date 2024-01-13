package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Lombok */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "addresses")
public class Address implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Lombok */
	@EqualsAndHashCode.Include
	
	@Id
	protected Long id;
	@Basic
	private String name;
	@Basic
	private String street;
	@Basic
	private String number;
	@Basic
	private String floor;
	@Basic
	private String door;
	@Basic
	private String city;
	@Basic
	private String province;
	@Basic
	private String postalCode;
	@Basic
	private String country;
}
