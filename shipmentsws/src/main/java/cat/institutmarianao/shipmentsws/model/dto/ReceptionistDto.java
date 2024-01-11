package cat.institutmarianao.shipmentsws.model.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/* Lombok */
@Data
@EqualsAndHashCode(callSuper = true)

public class ReceptionistDto extends UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int MAX_PLACE = 100;
	
	private Long officeId;

	private String place;

}
