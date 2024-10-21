package code.microsystem.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import code.microsystem.dto.DoctorRequest;
import code.microsystem.entity.Doctor;
import code.microsystem.exception.DoctorNotFoundException;
import code.microsystem.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/doctor")
public class DoctorController {

	@Autowired
	private DoctorService doctorService;

	@Operation(summary = "Get Application Health", description = "Get Application Health .")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Doctor.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping("/getHealth")
	public ResponseEntity<String> getHealth() {
		return new ResponseEntity<String>("Ayush Plus Project is Running Successfully for Good Health..",
				HttpStatus.OK);
	}

	@Operation(summary = "Add New Doctor", description = "Add New Doctor")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Doctor.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@PostMapping("/addDoctor")
	public ResponseEntity<Doctor> addDoctor(@Valid @RequestBody DoctorRequest doctorRequest) {
		Doctor doctor = doctorService.addDoctor(doctorRequest);
		System.out.print(doctor);
		return new ResponseEntity<Doctor>(doctor, HttpStatus.CREATED);
	}

	@Operation(summary = "Add Doctor list", description = "Add multiple Doctor information at a time .")
	@ApiResponses({
			@ApiResponse(responseCode = "201", content = {
					@Content(schema = @Schema(implementation = Doctor.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@PostMapping("/addDoctorList")
	public ResponseEntity<List<Doctor>> addDoctorList(@Valid @RequestBody List<Doctor> doctor) {
		List<Doctor> d = doctorService.addDoctorList(doctor);
		return new ResponseEntity<List<Doctor>>(d, HttpStatus.CREATED);
	}

	@Operation(summary = "Retrieve a Doctor by Id", description = "Get a Doctor object by specifying its id. The response is Doctor object .")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Doctor.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping("/getDoctorBydId/{dId}")
	public ResponseEntity<Doctor> getDoctorBydId(@PathVariable("dId") int dId) throws DoctorNotFoundException {
		Doctor doctor = doctorService.getDoctorBydId(dId);
		if (doctor == null) {
			throw new DoctorNotFoundException("Doctor Id Does Not Exists : " + dId);
		}
		return new ResponseEntity<>(doctor, HttpStatus.OK);
	}

	@Operation(summary = "Retrive All Doctor Details", description = "Get all Doctor detals by sending this request")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Doctor.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping("/getAllDoctor")
	public ResponseEntity<List<Doctor>> getAllDoctor() {
		List<Doctor> doctor = doctorService.getAllDoctor();
		return new ResponseEntity<List<Doctor>>(doctor, HttpStatus.OK);
	}

	@Operation(summary = "Delete Doctor by Doctor Id", description = "Delete Doctor by the Doctor Id")
	@ApiResponses({
			@ApiResponse(responseCode = "202", content = {
					@Content(schema = @Schema(implementation = Doctor.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@DeleteMapping("/deleteDoctorBydId/{dId}")
	public ResponseEntity<String> deleteDoctorBydId(@PathVariable("dId") int dId) throws DoctorNotFoundException {
		doctorService.deleteDoctorBydId(dId);
		return new ResponseEntity<>("Doctor Record Deleted :" + dId, HttpStatus.ACCEPTED);
	}

	@Operation(summary = "Find FirstName And Age by Doctor ID ", description = "Find by Doctor Id")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Doctor.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })

	@GetMapping("/findByFirstNameAndAge")
	public ResponseEntity<List<Doctor>> findByFirstNameAndAge(@RequestParam("firstName") String firstName,
			@RequestParam("age") int age) throws DoctorNotFoundException {
		List<Doctor> list = new ArrayList<>();
		list = doctorService.findByFirstNameAndAge(firstName, age);
		if (list.isEmpty())
			throw new DoctorNotFoundException("Doctor Record Not Found.....");
		return new ResponseEntity<List<Doctor>>(list, HttpStatus.OK);
	}

	@Operation(
			summary = "Find Doctor Email And Mobile Number by Id", description = "Get a Patient object by specifying its id. The response is Patient object .")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Doctor.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping("/getDoctorEmailAndMobileUsingId/{dId}")
	public ResponseEntity<Map<String, String>> getPatientDetails(@PathVariable int dId) {
		try {
			Doctor doctor = doctorService.getDoctorBydId(dId);
			Map<String, String> res = new HashMap<>();
			res.put("email", doctor.getEmail());
			res.put("mobile", doctor.getMobile());
			return ResponseEntity.ok(res);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(Collections.singletonMap("error", "Patient Id does not exists....." + dId));
		}

	}

	@Operation(
			summary = "Edit  Doctor Email And Mobile Number by Id", description = "Edit Patient object by specifying its id. The response is Patient object .")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Doctor.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@PutMapping("/editDoctor")
	public ResponseEntity<Doctor> editDoctor(@RequestBody @Valid DoctorRequest doctorRequest)
			throws DoctorNotFoundException {
		Doctor doctor = doctorService.editDoctor(doctorRequest);
		return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
	}
}
