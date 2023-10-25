package cat.institutmarianao.shipmentsws.model.convert.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import cat.institutmarianao.shipmentsws.model.Action;
import cat.institutmarianao.shipmentsws.model.Assignment;
import cat.institutmarianao.shipmentsws.model.Courier;
import cat.institutmarianao.shipmentsws.model.Delivery;
import cat.institutmarianao.shipmentsws.model.Reception;
import cat.institutmarianao.shipmentsws.model.User;
import cat.institutmarianao.shipmentsws.model.dto.ActionDto;
import cat.institutmarianao.shipmentsws.model.dto.AssignmentDto;
import cat.institutmarianao.shipmentsws.model.dto.DeliveryDto;
import cat.institutmarianao.shipmentsws.model.dto.ReceptionDto;
import cat.institutmarianao.shipmentsws.services.UserService;

@Component
public class ActionDtoToActionConverter implements Converter<ActionDto, Action> {

	@Autowired
	private UserService userService;

	@Override
	public Action convert(ActionDto actionDto) {
		if (actionDto == null) {
			return null;
		}

		if (actionDto instanceof ReceptionDto receptionDto) {
			Reception reception = new Reception();
			copyCommonProperties(receptionDto, reception);
			return reception;
		}
		if (actionDto instanceof AssignmentDto assignmentDto) {
			Assignment assignment = new Assignment();
			copyCommonProperties(assignmentDto, assignment);
			User user = userService.getByUsername(assignmentDto.getCourier());
			if (user instanceof Courier courier) {
				assignment.setCourier(courier);
			}
			return assignment;
		}
		if (actionDto instanceof DeliveryDto deliveryDto) {
			Delivery delivery = new Delivery();
			copyCommonProperties(deliveryDto, delivery);
			return delivery;
		}
		return null;
	}

	private void copyCommonProperties(ActionDto actionDto, Action action) {
		BeanUtils.copyProperties(actionDto, action);
		action.setDate(actionDto.getDate());
		User performer = userService.getByUsername(actionDto.getPerformer());
		action.setPerformer(performer);
		// TODO Copy shipment
	}
}
