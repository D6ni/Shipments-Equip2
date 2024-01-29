package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	protected Long id;
	
	@Column(unique = true, nullable = false)
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
	
	@Column(name = "postal_code")
	private String postalCode;
	@Basic
	private String country;
}
