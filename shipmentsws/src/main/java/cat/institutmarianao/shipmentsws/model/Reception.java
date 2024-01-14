package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Lombok */
@Data
@EqualsAndHashCode(callSuper = true)

@Entity
@DiscriminatorValue(Action.RECEPTION)
public class Reception extends Action implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Column(name = "tracking_number")
	private Integer trackingNumber;
}
