package cat.institutmarianao.shipmentsws.model.dto;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Lombok */
@Data
@EqualsAndHashCode(callSuper = true)

/* JPA */
@Entity
@Table(name = "receptionists")
public class ReceptionistDto extends UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int MAX_PLACE = 100;
	
	@Column(name = "office_id", nullable = false)
	private Long officeId;

	@Column(name = "place")
	private String place;

}
