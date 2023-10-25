package cat.institutmarianao.shipmentsws.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import cat.institutmarianao.shipmentsws.ShipmentswsApplication;
import cat.institutmarianao.shipmentsws.model.Action;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Swagger */
@Schema(oneOf = { ReceptionDto.class, AssignmentDto.class, DeliveryDto.class }, discriminatorProperty = "type")
/* Lombok */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class ActionDto implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Lombok */
	@EqualsAndHashCode.Include
	protected Long id;

	protected Action.Type type;

	protected String performer;

	/* Swagger */
	@Schema(pattern = ShipmentswsApplication.DATE_PATTERN)
	/* JSON */
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = ShipmentswsApplication.DATE_PATTERN)
	protected Date date = new Date();

	protected Long shipmentId;
}
