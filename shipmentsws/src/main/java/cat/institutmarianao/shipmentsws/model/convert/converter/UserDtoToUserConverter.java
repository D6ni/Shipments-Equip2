package cat.institutmarianao.shipmentsws.model.convert.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import cat.institutmarianao.shipmentsws.model.Courier;
import cat.institutmarianao.shipmentsws.model.LogisticsManager;
import cat.institutmarianao.shipmentsws.model.Receptionist;
import cat.institutmarianao.shipmentsws.model.User;
import cat.institutmarianao.shipmentsws.model.dto.CourierDto;
import cat.institutmarianao.shipmentsws.model.dto.LogisticsManagerDto;
import cat.institutmarianao.shipmentsws.model.dto.ReceptionistDto;
import cat.institutmarianao.shipmentsws.model.dto.UserDto;

@Component
public class UserDtoToUserConverter implements Converter<UserDto, User> {

	@Override
	public User convert(UserDto userDto) {
		if (userDto instanceof LogisticsManagerDto logisticsManagerDto) {
			// Includes Supervisor
			LogisticsManager logisticsManager = new LogisticsManager();
			copyCommonProperties(logisticsManagerDto, logisticsManager);

			// TODO Copy office
			logisticsManager.setPlace(logisticsManagerDto.getPlace());

			return logisticsManager;
		}
		if (userDto instanceof ReceptionistDto receptionistDto) {
			Receptionist receptionist = new Receptionist();
			copyCommonProperties(receptionistDto, receptionist);

			// TODO Copy office
			receptionist.setPlace(receptionistDto.getPlace());
			return receptionist;
		}
		if (userDto instanceof CourierDto courierDto) {
			// Includes Supervisor
			Courier courier = new Courier();
			copyCommonProperties(courierDto, courier);

			// TODO Copy company
			return courier;
		}
		return null;
	}

	private void copyCommonProperties(UserDto userDto, User user) {
		BeanUtils.copyProperties(userDto, user);
	}
}
