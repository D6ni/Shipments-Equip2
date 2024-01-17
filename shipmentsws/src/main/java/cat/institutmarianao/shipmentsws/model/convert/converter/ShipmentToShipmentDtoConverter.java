package cat.institutmarianao.shipmentsws.model.convert.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import cat.institutmarianao.shipmentsws.model.Assignment;
import cat.institutmarianao.shipmentsws.model.Reception;
import cat.institutmarianao.shipmentsws.model.Shipment;
import cat.institutmarianao.shipmentsws.model.convert.exception.ActionToTicketDtoConversionException;
import cat.institutmarianao.shipmentsws.model.dto.ShipmentDto;

@Component
public class ShipmentToShipmentDtoConverter implements Converter<Shipment, ShipmentDto> {

	@Override
	public ShipmentDto convert(Shipment shipment) {
		if (shipment == null) {
			return null;
		}
		if (!(shipment.getTracking().get(shipment.getTracking().size() - 1) instanceof Reception reception)) {
			throw new ActionToTicketDtoConversionException("First action of a Shipment must be a Reception");
		}

		ShipmentDto shipmentDto = new ShipmentDto();

		if (shipment.getTracking().size() > 1) {
			if (!(shipment.getTracking().get(shipment.getTracking().size() - 2) instanceof Assignment assignment)) {
				throw new ActionToTicketDtoConversionException("Second action of a Shipment must be an Assignment");
			}
			shipmentDto.setPriority(assignment.getPriority());
			shipmentDto.setCourier(assignment.getCourier().getUsername());
		}

		BeanUtils.copyProperties(shipment, shipmentDto);
		
		shipmentDto.setReceptionist(reception.getPerformer().getUsername());
		shipmentDto.setReceptionDate(reception.getDate());
		shipmentDto.setTrackingNumber(reception.getTrackingNumber());

		return shipmentDto;
	}

}
