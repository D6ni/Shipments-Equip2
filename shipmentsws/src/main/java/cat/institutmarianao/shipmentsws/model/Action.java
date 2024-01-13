package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Lombok */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
/* JPA */
@Entity
@Table(name = "actions")
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
	@Basic
	protected Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "type")
	protected Type type;
	
	@ManyToOne
	@JoinColumn(name = "performer_username", referencedColumnName = "username")
	protected User performer;

	@Basic
	protected Date date = new Date();
	
	@ManyToOne
	@JoinColumn(name = "shipment_id", referencedColumnName = "id")
	protected Shipment shipment;
}
