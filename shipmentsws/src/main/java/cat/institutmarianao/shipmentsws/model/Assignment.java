package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;

import jakarta.persistence.Basic;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Lombok */
@Data
@EqualsAndHashCode(callSuper = true)

@Entity
@DiscriminatorValue(Action.ASSIGNMENT)
public class Assignment extends Action implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int MIN_PRIORITAT = 1;
	public static final int MAX_PRIORITAT = 3;

	@ManyToOne(optional = false)
	@JoinColumn(name = "courier_username", referencedColumnName = "username")
	private Courier courier;
	@Basic
	private Integer priority;

}
