package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;
import java.util.List;

import org.hibernate.annotations.Formula;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

/* Lombok */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "shipments")
public class Shipment implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int MAX_DESCRIPTION = 500;

	public enum Category {
		PARTICULAR, COMPANY, GOVERNMENT
	}

	public static final String PENDING = "PENDING";
	public static final String IN_PROCESS = "IN_PROCESS";
	public static final String DELIVERED = "DELIVERED";

	public enum Status {
		PENDING, IN_PROCESS, DELIVERED
	}

	/* Lombok */
	@EqualsAndHashCode.Include
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	@NotNull
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Category category;

	@ManyToOne(optional = false)
	@JoinColumn(name = "sender_id", nullable = false)
	private Address sender;

	@ManyToOne(optional = false)
	@JoinColumn(name = "recipient_id", nullable = false)
	private Address recipient;

	@Basic
	private Float weight;
	@Basic
	private Float height;
	@Basic
	private Float width;
	@Basic
	private Float length;
	@Basic
	private Boolean express;
	@Basic
	private Boolean fragile;
	@Basic
	private String note;


	/* JPA */
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "shipment")
	@Column(nullable = false)
	@OrderBy("date DESC")
	private List<Action> tracking;

	/* JPA */
	@Enumerated(EnumType.STRING)
	/* Hibernate */
	@Formula("(SELECT CASE a.type WHEN '" + Action.RECEPTION + "' THEN '" + PENDING + "' " + " WHEN '"
			+ Action.ASSIGNMENT + "' THEN '" + IN_PROCESS + "' " + " WHEN '" + Action.DELIVERY + "' THEN '" + DELIVERED
			+ "' ELSE NULL END FROM actions a "
			+ " WHERE a.date=( SELECT MAX(last_action.date) FROM actions last_action "
			+ " WHERE last_action.shipment_id=a.shipment_id AND last_action.shipment_id=id ))")
	// Lombok
	@Setter(AccessLevel.NONE)
	private Status status;

}
