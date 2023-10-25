package cat.institutmarianao.shipmentsws.model.convert.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import cat.institutmarianao.shipmentsws.model.Action;
import cat.institutmarianao.shipmentsws.model.Assignment;
import cat.institutmarianao.shipmentsws.model.Delivery;
import cat.institutmarianao.shipmentsws.model.Reception;
import cat.institutmarianao.shipmentsws.model.dto.ActionDto;
import cat.institutmarianao.shipmentsws.model.dto.AssignmentDto;
import cat.institutmarianao.shipmentsws.model.dto.DeliveryDto;
import cat.institutmarianao.shipmentsws.model.dto.ReceptionDto;

@Component
public class ActionToActionDtoConverter implements Converter<Action, ActionDto> {

	@Override
	public ActionDto convert(Action action) {
		if (action instanceof Reception reception) {
			ReceptionDto receptionDto = new ReceptionDto();
			copyCommonProperties(reception, receptionDto);
			return receptionDto;
		}
		if (action instanceof Assignment assignment) {
			AssignmentDto assignmentDto = new AssignmentDto();
			copyCommonProperties(assignment, assignmentDto);
			assignmentDto.setCourier((assignment.getCourier().getUsername()));
			assignmentDto.setPriority(assignment.getPriority());
			return assignmentDto;
		}
		if (action instanceof Delivery delivery) {
			DeliveryDto deliveryDto = new DeliveryDto();
			copyCommonProperties(delivery, deliveryDto);
			return deliveryDto;
		}
		return null;
	}

	private void copyCommonProperties(Action action, ActionDto actionDto) {
		BeanUtils.copyProperties(action, actionDto);
		actionDto.setPerformer(action.getPerformer().getUsername());
		if (action.getShipment() != null) {
			actionDto.setShipmentId(action.getShipment().getId());
		}
	}
}
