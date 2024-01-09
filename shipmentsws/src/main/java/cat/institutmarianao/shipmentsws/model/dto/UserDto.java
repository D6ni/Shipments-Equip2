package cat.institutmarianao.shipmentsws.model.dto;

import java.io.Serializable;

import cat.institutmarianao.shipmentsws.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* JPA*/
@Entity
@Table(name = "USERS")
/* Swagger */
@Schema(oneOf = { ReceptionistDto.class, LogisticsManagerDto.class, CourierDto.class }, discriminatorProperty = "role")
/* Lombok */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class UserDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/* Lombok */
	@EqualsAndHashCode.Include
	
	/* JPA*/
	@Id
	protected String username;
	
	@Column(name = "role")
	protected User.Role role;
	
	
	@Column(name = "password")
	protected String password;
	
	@Column(name = "full_name")
	protected String fullName;
	
	@Column(name = "extension")
	protected Integer extension;
	
	@Column(name = "place")
	protected String location;

	
}
