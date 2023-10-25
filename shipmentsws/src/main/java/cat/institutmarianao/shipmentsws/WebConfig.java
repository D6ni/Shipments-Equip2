package cat.institutmarianao.shipmentsws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import cat.institutmarianao.shipmentsws.model.convert.converter.ActionDtoToActionConverter;
import cat.institutmarianao.shipmentsws.model.convert.converter.ActionToActionDtoConverter;
import cat.institutmarianao.shipmentsws.model.convert.converter.ShipmentDtoToShipmentConverter;
import cat.institutmarianao.shipmentsws.model.convert.converter.ShipmentToShipmentDtoConverter;
import cat.institutmarianao.shipmentsws.model.convert.converter.UserDtoToUserConverter;
import cat.institutmarianao.shipmentsws.model.convert.converter.UserToUserDtoConverter;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	@Autowired
	private ActionDtoToActionConverter actionDtoToActionConverter;

	@Autowired
	private ActionToActionDtoConverter actionToActionDtoConverter;

	@Autowired
	private ShipmentDtoToShipmentConverter shipmentDtoToTicketConverter;

	@Autowired
	private ShipmentToShipmentDtoConverter shipmentToShipmentDtoConverter;

	@Autowired
	private UserToUserDtoConverter userToUserDtoConverter;

	@Autowired
	private UserDtoToUserConverter userDtoToUserConverter;

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(actionDtoToActionConverter);
		registry.addConverter(actionToActionDtoConverter);
		registry.addConverter(shipmentDtoToTicketConverter);
		registry.addConverter(shipmentToShipmentDtoConverter);
		registry.addConverter(userToUserDtoConverter);
		registry.addConverter(userDtoToUserConverter);
	}
}