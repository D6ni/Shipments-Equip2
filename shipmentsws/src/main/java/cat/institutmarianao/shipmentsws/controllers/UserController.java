package cat.institutmarianao.shipmentsws.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cat.institutmarianao.shipmentsws.exception.ForbiddenException;
import cat.institutmarianao.shipmentsws.model.User;
import cat.institutmarianao.shipmentsws.model.User.Role;
import cat.institutmarianao.shipmentsws.model.dto.CourierDto;
import cat.institutmarianao.shipmentsws.model.dto.LogisticsManagerDto;
import cat.institutmarianao.shipmentsws.model.dto.ReceptionistDto;
import cat.institutmarianao.shipmentsws.model.dto.UserDto;
import cat.institutmarianao.shipmentsws.services.UserService;
import cat.institutmarianao.shipmentsws.validation.groups.OnUserCreate;
import cat.institutmarianao.shipmentsws.validation.groups.OnUserUpdate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

/* Swagger */
@Tag(name = "User", description = "UserController API")
/**/
@RestController
@RequestMapping("/users")
@Validated
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private ConversionService conversionService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	/* Swagger */
	@Operation(summary = "Authenticate a user")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
			@Content(mediaType = "application/json", examples = {
					@ExampleObject(value = "{\"username\":\"string\",\"password\":\"string\"}") }) })
	@ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(oneOf = {
			ReceptionistDto.class, LogisticsManagerDto.class,
			CourierDto.class }, discriminatorProperty = "role")) }, description = "User authenticated ok")
	@ApiResponse(responseCode = "404", content = {
			@Content(mediaType = "application/json") }, description = "Resource not found")
	/**/
	@PostMapping("/authenticate")
	public UserDto authenticate(@RequestBody Map<String, String> usernamePassword) {

		User user = userService.getByUsername(usernamePassword.get("username"));

		if (user != null && passwordEncoder.matches(usernamePassword.get("password"), user.getPassword())) {
			return conversionService.convert(user, UserDto.class);
		}

		throw new ForbiddenException();
	}

	/* Swagger */
	@Operation(summary = "Find all users")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(anyOf = {
					ReceptionistDto.class, LogisticsManagerDto.class,
					CourierDto.class }, discriminatorProperty = "role"))) }, description = "Users retrieved ok")
	/**/
	@GetMapping(value = "/find/all")
	public List<UserDto> findAll(@RequestParam(value = "roles", required = false) Role[] roles,
			@RequestParam(value = "fullName", required = false) String fullName) {

		List<User> users = userService.findAll(roles, fullName);

		List<UserDto> usersDto = new ArrayList<>(users.size());
		for (User user : users) {
			UserDto userDto = conversionService.convert(user, UserDto.class);
			usersDto.add(userDto);
		}

		return usersDto;
	}

	/* Swagger */
	@Operation(summary = "Get user by id")
	@ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(anyOf = {
			ReceptionistDto.class, LogisticsManagerDto.class,
			CourierDto.class }, discriminatorProperty = "role")) }, description = "User retrieved ok")
	@ApiResponse(responseCode = "404", content = {
			@Content(mediaType = "application/json") }, description = "Resource not found")
	/**/
	@GetMapping("/get/by/username/{username}")
	public UserDto findByUsername(@PathVariable("username") @NotBlank String username) {

		User opUser = userService.getByUsername(username);
		return conversionService.convert(opUser, UserDto.class);
	}

	/* Swagger */
	@Operation(summary = "Save a user")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(content = {
			@Content(mediaType = "application/json", schema = @Schema(oneOf = { ReceptionistDto.class,
					LogisticsManagerDto.class, CourierDto.class }, discriminatorProperty = "role")) })
	@ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(anyOf = {
			ReceptionistDto.class, LogisticsManagerDto.class,
			CourierDto.class }, discriminatorProperty = "role")) }, description = "User saved ok")
	/**/
	@PostMapping("/save")
	@Validated(OnUserCreate.class)
	public UserDto save(@RequestBody @Valid UserDto userDto) {
		return conversionService.convert(userService.save(convertAndEncodePassword(userDto)), UserDto.class);
	}

	/* Swagger */
	@Operation(summary = "Update a user")
	@ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json", schema = @Schema(anyOf = {
			ReceptionistDto.class, LogisticsManagerDto.class,
			CourierDto.class }, discriminatorProperty = "role")) }, description = "User updated ok")
	@ApiResponse(responseCode = "404", content = {
			@Content(mediaType = "application/json") }, description = "Resource not found")
	/**/
	@PutMapping("/update")
	@Validated(OnUserUpdate.class)
	public UserDto update(@RequestBody @Valid UserDto userDto) {
		return conversionService.convert(userService.update(convertAndEncodePassword(userDto)), UserDto.class);
	}

	/* Swagger */
	@Operation(summary = "Delete a user")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json") }, description = "User deleted ok")
	/**/
	@DeleteMapping("/delete/by/username/{username}")
	public void deleteByUsername(@PathVariable("username") @NotBlank String username) {
		userService.deleteByUsername(username);
	}

	private User convertAndEncodePassword(UserDto userDto) {

		String rawPassword = userDto.getPassword();
		if (rawPassword != null) {
			userDto.setPassword(passwordEncoder.encode(rawPassword));
		}
		return conversionService.convert(userDto, User.class);
	}
}
