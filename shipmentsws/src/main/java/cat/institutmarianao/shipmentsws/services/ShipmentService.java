package cat.institutmarianao.shipmentsws.services;

import java.util.Optional;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import cat.institutmarianao.shipmentsws.model.Shipment;

public interface ShipmentService {

	public Optional<Shipment> findById(@Positive Long id);

	Shipment save(@NotNull @Valid Shipment shipment);

	void deleteById(@Positive Long shipmentId);

}