package cat.institutmarianao.shipmentsws.model.dto;

import java.io.Serializable;

import cat.institutmarianao.shipmentsws.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Swagger */
@Schema(oneOf = { ReceptionistDto.class, LogisticsManagerDto.class, CourierDto.class }, discriminatorProperty = "role")
/* Lombok */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Lombok */
	@EqualsAndHashCode.Include
	protected String username;

	protected User.Role role;

	protected String password;

	protected String fullName;

	protected Integer extension;

	protected String location;

}
