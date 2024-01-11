package cat.institutmarianao.shipmentsws.model.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;

/* Lombok */
@Data
@EqualsAndHashCode(callSuper = true)
public class ReceptionDto extends ActionDto implements Serializable {

	private static final long serialVersionUID = 1L;

}
