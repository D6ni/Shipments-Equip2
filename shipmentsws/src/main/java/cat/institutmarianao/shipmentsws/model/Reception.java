package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Lombok */
@Data
@EqualsAndHashCode(callSuper = true)

@Table(name = "actions")
public class Reception extends Action implements Serializable {

	private static final long serialVersionUID = 1L;
	@Column(name = "tracking_number")
	private Integer trackingNumber;
}
