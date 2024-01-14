package cat.institutmarianao.shipmentsws.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

import cat.institutmarianao.shipmentsws.model.Action;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Swagger */
@Schema(oneOf = { ReceptionDto.class, AssignmentDto.class, DeliveryDto.class }, discriminatorProperty = "type")
/* Lombok */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXISTING_PROPERTY, property = "type", visible = true)
@JsonSubTypes({ @Type(value = DeliveryDto.class, name = Action.DELIVERY),
		@Type(value = AssignmentDto.class, name = Action.ASSIGNMENT),
		@Type(value = ReceptionDto.class, name = Action.RECEPTION)})
public abstract class ActionDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull
    protected Action.Type type;

 
    private String performer;

    /* JSON */
	@JsonFormat(shape = JsonFormat.Shape.STRING)
    private Date date = new Date();

	@NotNull
    private Long shipmentId;
}
