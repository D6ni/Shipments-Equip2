package cat.institutmarianao.shipmentsws.services;

import java.util.List;
import java.util.Optional;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import cat.institutmarianao.shipmentsws.model.Shipment;
import cat.institutmarianao.shipmentsws.model.Shipment.Status;

public interface ShipmentService {

	public Optional<Shipment> findById(@Positive Long id);

	Shipment save(@NotNull @Valid Shipment shipment);

	void deleteById(@Positive Long shipmentId);
	
	List<Shipment> findAll();
	
	List<Shipment> findAllByStatus(Status status);
	
	Shipment getById(Long shipmentId);

}