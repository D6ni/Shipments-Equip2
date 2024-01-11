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
public class AssignmentDto extends ActionDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final int MIN_PRIORITAT = 1;
	public static final int MAX_PRIORITAT = 9;

	private String courier;

	private Integer priority;

}
