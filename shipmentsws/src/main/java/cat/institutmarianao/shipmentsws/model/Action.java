package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Lombok */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
/* JPA */
@Entity
@Table(name = "actions", indexes = { @Index(name = "type", columnList = "type", unique = false),
		@Index(name = "shipment_x_date", columnList = "shipment_id, date DESC", unique = true) })
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Action implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Values for type - MUST be constants */
	public static final String RECEPTION = "RECEPTION";
	public static final String ASSIGNMENT = "ASSIGNMENT";
	public static final String DELIVERY = "DELIVERY";

	public enum Type {
		RECEPTION, ASSIGNMENT, DELIVERY
	}

	/* Lombok */
	@EqualsAndHashCode.Include
	/* JPA */
	@Id
	@NotNull
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	protected Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type", insertable = false, updatable = false, nullable = false)
	protected Type type;
	
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	protected User performer;

	@Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	protected Date date = new Date();
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "shipment_id", referencedColumnName = "id")
	protected Shipment shipment;
}
