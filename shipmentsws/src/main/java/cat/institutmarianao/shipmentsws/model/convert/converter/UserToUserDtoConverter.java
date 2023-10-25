package cat.institutmarianao.shipmentsws.model.convert.converter;

import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import cat.institutmarianao.shipmentsws.model.Company;
import cat.institutmarianao.shipmentsws.model.Courier;
import cat.institutmarianao.shipmentsws.model.LogisticsManager;
import cat.institutmarianao.shipmentsws.model.Receptionist;
import cat.institutmarianao.shipmentsws.model.User;
import cat.institutmarianao.shipmentsws.model.dto.CourierDto;
import cat.institutmarianao.shipmentsws.model.dto.LogisticsManagerDto;
import cat.institutmarianao.shipmentsws.model.dto.ReceptionistDto;
import cat.institutmarianao.shipmentsws.model.dto.UserDto;

@Component
public class UserToUserDtoConverter implements Converter<User, UserDto> {

	@Override
	public UserDto convert(User user) {
		if (user instanceof LogisticsManager logisticsManager) {
			LogisticsManagerDto logisticsManagerDto = new LogisticsManagerDto();

			copyCommonProperties(logisticsManager, logisticsManagerDto);

			logisticsManagerDto.setOfficeId(logisticsManager.getOffice().getId());
			logisticsManagerDto.setPlace(logisticsManager.getPlace());

			logisticsManagerDto.setLocation(
					String.format("%s (%s)", logisticsManager.getOffice().getName(), logisticsManager.getPlace()));

			return logisticsManagerDto;
		}
		if (user instanceof Receptionist receptionist) {
			ReceptionistDto receptionistDto = new ReceptionistDto();

			copyCommonProperties(receptionist, receptionistDto);

			receptionistDto.setOfficeId(receptionist.getOffice().getId());
			receptionistDto.setPlace(receptionist.getPlace());

			receptionistDto
					.setLocation(String.format("%s (%s)", receptionist.getOffice().getName(), receptionist.getPlace()));

			return receptionistDto;
		}
		if (user instanceof Courier courier) {
			CourierDto courierDto = new CourierDto();

			copyCommonProperties(courier, courierDto);

			Company company = courier.getCompany();
			courierDto.setCompanyId(company.getId());

			courierDto.setLocation(company.getName());

			return courierDto;
		}
		return null;
	}

	private void copyCommonProperties(User user, UserDto userDto) {
		BeanUtils.copyProperties(user, userDto);
	}
}
