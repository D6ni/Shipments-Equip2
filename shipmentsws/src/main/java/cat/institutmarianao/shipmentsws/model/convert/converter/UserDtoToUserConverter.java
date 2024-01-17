package cat.institutmarianao.shipmentsws.model.convert.converter;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import cat.institutmarianao.shipmentsws.model.Courier;
import cat.institutmarianao.shipmentsws.model.LogisticsManager;
import cat.institutmarianao.shipmentsws.model.Office;
import cat.institutmarianao.shipmentsws.model.Receptionist;
import cat.institutmarianao.shipmentsws.model.User;
import cat.institutmarianao.shipmentsws.model.convert.exception.ActionToTicketDtoConversionException;
import cat.institutmarianao.shipmentsws.model.dto.CourierDto;
import cat.institutmarianao.shipmentsws.model.dto.LogisticsManagerDto;
import cat.institutmarianao.shipmentsws.model.dto.ReceptionistDto;
import cat.institutmarianao.shipmentsws.model.dto.UserDto;
import cat.institutmarianao.shipmentsws.services.OfficeService;

@Component
public class UserDtoToUserConverter implements Converter<UserDto, User> {
	
	@Autowired
	private OfficeService officeService;

	@Override
	public User convert(UserDto userDto) {
		if (userDto instanceof LogisticsManagerDto logisticsManagerDto) {
			// Includes Supervisor
			LogisticsManager logisticsManager = new LogisticsManager();
			copyCommonProperties(logisticsManagerDto, logisticsManager);

			// TODO Copy office
			Optional<Office> office = officeService.findById(logisticsManagerDto.getOfficeId());
			if (office.isEmpty()) {
				throw new ActionToTicketDtoConversionException("Company not found " + logisticsManagerDto.getOfficeId());
			}
			logisticsManager.setPlace(logisticsManagerDto.getPlace());

			return logisticsManager;
		}
		if (userDto instanceof ReceptionistDto receptionistDto) {
			Receptionist receptionist = new Receptionist();
			copyCommonProperties(receptionistDto, receptionist);

			// TODO Copy office
			Optional<Office> office = officeService.findById(receptionistDto.getOfficeId());
            if (office.isEmpty()) {
                throw new ActionToTicketDtoConversionException("Company not found " + receptionistDto.getOfficeId());
            }
			receptionist.setPlace(receptionistDto.getPlace());
			return receptionist;
		}
		if (userDto instanceof CourierDto courierDto) {
			// Includes Supervisor
			Courier courier = new Courier();
			copyCommonProperties(courierDto, courier);

			// TODO Copy company
			Optional<Office> company = officeService.findById(courierDto.getCompanyId());
            if (company.isEmpty()) {
                throw new ActionToTicketDtoConversionException("Company not found " + courierDto.getCompanyId());
            }
			return courier;
		}
		return null;
	}

	private void copyCommonProperties(UserDto userDto, User user) {
		BeanUtils.copyProperties(userDto, user);
	}
}
