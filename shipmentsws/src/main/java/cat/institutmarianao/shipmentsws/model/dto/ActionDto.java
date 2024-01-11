package cat.institutmarianao.shipmentsws.model.dto;

import java.io.Serializable;
import java.util.Date;

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

    private Long id;

    protected Action.Type type;

 
    private String performer;

    private Date date = new Date();

    private Long shipmentId;
}
