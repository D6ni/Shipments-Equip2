package cat.institutmarianao.shipmentsws.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import cat.institutmarianao.shipmentsws.exception.NotFoundException;
import cat.institutmarianao.shipmentsws.model.Shipment;
import cat.institutmarianao.shipmentsws.model.Shipment.Status;
import cat.institutmarianao.shipmentsws.repositories.ShipmentRepository;
import cat.institutmarianao.shipmentsws.services.ShipmentService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class ShipmentServiceImpl implements ShipmentService {
	
	@Autowired
	private ShipmentRepository shipmentRepository;


	@Override
	public Optional<Shipment> findById(@Positive Long id) {
		return shipmentRepository.findById(id);
	}

	@Override
	public Shipment save(@NotNull @Valid Shipment shipment) {
		return shipmentRepository.save(shipment);
	}

	@Override
	public void deleteById(@Positive Long ticketId) {
		shipmentRepository.deleteById(ticketId);
	}
	
	@Override
	public List<Shipment> findAll() {

		return shipmentRepository.findAll();
	}
	
	@Override
	public List<Shipment> findAllByStatus(Status status) {
		return shipmentRepository.findAllByStatusOrderById(status);
	}

	@Override
	public Shipment getById(Long shipmentId) {
		return shipmentRepository.findById(shipmentId).orElseThrow(NotFoundException::new);
	}

	
}