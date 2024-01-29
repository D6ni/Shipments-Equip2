package cat.institutmarianao.shipmentsws.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.Range;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

/* Lombok */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

@Entity
@Table(name = "users", indexes = { @Index(name = "role", columnList = "role", unique = false),
		@Index(name = "full_name", columnList = "full_name", unique = false),
		@Index(name = "role_x_full_name", columnList = "role, full_name", unique = false) })
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role", discriminatorType = DiscriminatorType.STRING)
public abstract class User implements Serializable {

	private static final long serialVersionUID = 1L;

	/* Values for role - MUST be constants (can not be enums) */
	public static final String RECEPTIONIST = "RECEPTIONIST";
	public static final String LOGISTICS_MANAGER = "LOGISTICS_MANAGER";
	public static final String COURIER = "COURIER";

	public enum Role {
		RECEPTIONIST, LOGISTICS_MANAGER, COURIER
	}

	public static final int MIN_USERNAME = 2;
	public static final int MAX_USERNAME = 25;
	public static final int MIN_PASSWORD = 10;
	public static final int MIN_FULL_NAME = 3;
	public static final int MAX_FULL_NAME = 100;
	public static final int MAX_EXTENSION = 9999;

	/* Lombok */
	@EqualsAndHashCode.Include
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
    @Size(min = MIN_USERNAME, max = MAX_USERNAME)
	protected String username;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", insertable = false, updatable = false, nullable = false)
	protected Role role;

	@NotBlank
    @Size(min = MIN_PASSWORD, max = 255)
	protected String password;

	@Column(name = "full_name", nullable = false)
	@NotNull
	@Size(min = MIN_FULL_NAME, max = MAX_FULL_NAME)
	protected String fullName;

	@Basic
	@Range(max = MAX_EXTENSION)
	protected Integer extension;
}
