package cat.institutmarianao.shipmentsws.model.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Lombok */
@Data
@EqualsAndHashCode(callSuper = true)
public class AssignmentDto extends ActionDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int MIN_PRIORITAT = 1;
	public static final int MAX_PRIORITAT = 9;

	@NotEmpty
	private String courier;

	@NotNull
	@Min(MIN_PRIORITAT)
	@Max(MAX_PRIORITAT)
	private Integer priority;

}
