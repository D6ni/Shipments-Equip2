package cat.institutmarianao.shipmentsws.model.dto;

import java.io.Serializable;

import cat.institutmarianao.shipmentsws.validation.groups.OnUserCreate;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Lombok */
@Data
@EqualsAndHashCode(callSuper = true)
public class CourierDto extends UserDto implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull(groups = OnUserCreate.class)
	private Long companyId;

}
