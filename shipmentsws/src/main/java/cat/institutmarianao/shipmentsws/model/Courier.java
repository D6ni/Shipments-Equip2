package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Lombok */
@Data
@EqualsAndHashCode(callSuper = true)

@Entity
/* An employee is identified in the user table with role=EMPLOYEE */
@DiscriminatorValue(User.COURIER)
public class Courier extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id", referencedColumnName = "id", nullable = false)
	private Company company;
}
