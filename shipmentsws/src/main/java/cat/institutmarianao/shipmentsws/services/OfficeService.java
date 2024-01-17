package cat.institutmarianao.shipmentsws.services;

import java.util.List;
import java.util.Optional;

import cat.institutmarianao.shipmentsws.model.Office;
import jakarta.validation.constraints.Positive;

public interface OfficeService {

	List<Office> findAll();

	Optional<Office> findById(@Positive Long id);
}