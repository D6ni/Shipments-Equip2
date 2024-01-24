package cat.institutmarianao.shipmentsws.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cat.institutmarianao.shipmentsws.model.Shipment;

@Repository
public interface ShipmentRepository extends JpaRepository<Shipment, Long> {
}
