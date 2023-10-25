package cat.institutmarianao.shipmentsws.model.convert.converter;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import cat.institutmarianao.shipmentsws.model.Action;
import cat.institutmarianao.shipmentsws.model.Reception;
import cat.institutmarianao.shipmentsws.model.Shipment;
import cat.institutmarianao.shipmentsws.model.User;
import cat.institutmarianao.shipmentsws.model.dto.ShipmentDto;
import cat.institutmarianao.shipmentsws.services.UserService;

@Component
public class ShipmentDtoToShipmentConverter implements Converter<ShipmentDto, Shipment> {

	@Autowired
	private UserService userService;

	@Override
	public Shipment convert(ShipmentDto shipmentDto) {
		if (shipmentDto == null) {
			return null;
		}
		User storedUser = userService.getByUsername(shipmentDto.getReceptionist());

		Shipment shipment = new Shipment();

		BeanUtils.copyProperties(shipmentDto, shipment);

		shipment.setTracking(new ArrayList<>());

		Reception reception = new Reception();
		reception.setDate(shipmentDto.getReceptionDate());
		reception.setType(Action.Type.RECEPTION);
		reception.setTrackingNumber(shipmentDto.getTrackingNumber());
		reception.setPerformer(storedUser);
		reception.setShipment(shipment);

		shipment.getTracking().add(reception);

		return shipment;
	}

}
