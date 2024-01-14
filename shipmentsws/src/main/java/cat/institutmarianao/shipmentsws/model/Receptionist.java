package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Lombok */
@Data
@EqualsAndHashCode(callSuper = true)

@Entity
@DiscriminatorValue(User.RECEPTIONIST)
public class Receptionist extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int MAX_PLACE = 100;

	@ManyToOne(fetch = FetchType.EAGER)
	private Office office;
	
	@Size(max = MAX_PLACE)
	private String place;

}
