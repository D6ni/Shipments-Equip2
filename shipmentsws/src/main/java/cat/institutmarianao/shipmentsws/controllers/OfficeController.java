package cat.institutmarianao.shipmentsws.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.institutmarianao.shipmentsws.model.Office;
import cat.institutmarianao.shipmentsws.services.OfficeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Positive;

@Tag(name = "Offices", description = "OfficeController API")

@RestController
@RequestMapping("/offices")
@Validated
public class OfficeController {

	@Autowired
	private OfficeService officeService;

	@Operation(summary = "Find all offices")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Office.class))) }, description = "Offices retrieved ok")

	@GetMapping(value = "/find/all")
	public List<Office> findAll() {
		List<Office> office = officeService.findAll();
		return office;
	}

	@Operation(summary = "Get office by id")
	@ApiResponse(responseCode = "200", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = Office.class)) }, description = "Office retrieved ok")
	@ApiResponse(responseCode = "404", content = {
			@Content(mediaType = "application/json") }, description = "Office not found")

	@GetMapping("/get/by/id/{id}")
	public Office getById(@PathVariable("id") @Positive Long id) {
		// find an office by id
		return officeService.getById(id);
	}
}
