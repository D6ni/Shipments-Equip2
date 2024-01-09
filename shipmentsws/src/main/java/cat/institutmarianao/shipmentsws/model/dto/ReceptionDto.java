package cat.institutmarianao.shipmentsws.model.dto;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Lombok */
@Data
@EqualsAndHashCode(callSuper = true)
/* JPA */
@Entity
@Table(name = "receptions")
public class ReceptionDto extends ActionDto implements Serializable {

	private static final long serialVersionUID = 1L;

}
