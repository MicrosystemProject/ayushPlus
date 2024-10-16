
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

import code.microsystem.dto.PatientRequest;
import code.microsystem.entity.Patient;
import code.microsystem.exception.PatientNotFoundException;
import code.microsystem.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/patient")
//patient controller
public class PatientController {

	@Autowired
	private PatientService patientService;

	@Operation(summary = "Get Application Health", description = "Get Application Health .")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Patient.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping("/getHealth")
	public ResponseEntity<String> getHealth() {
		return new ResponseEntity<String>("Ayush Plus Application Running with good Health..", HttpStatus.OK);
	}
	

	@Operation(summary = "Add New Patient ", description = "Add New Patient  .")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Patient.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@PostMapping("/addPatient")
	public ResponseEntity<Patient> addPatient(@Valid @RequestBody PatientRequest patientRequest) {
		
		Patient patient = patientService.addPatient(patientRequest);
		System.out.print(patient);
		return new ResponseEntity<Patient>(patient, HttpStatus.CREATED);

	}
	
	
	@Operation(summary = "Add Patient list", description = "Add multiple patients information at a time .")
	@ApiResponses({
			@ApiResponse(responseCode = "201", content = {
					@Content(schema = @Schema(implementation = Patient.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@PostMapping("/addPatientList")
	public ResponseEntity<List<Patient>> addPatientList(@Valid @RequestBody List<Patient> patients) {
		List<Patient> p = patientService.addPatientList(patients);
		return new ResponseEntity<List<Patient>>(p, HttpStatus.CREATED);
	}
	
	
	@Operation(summary = "Retrieve a Patient by Id", description = "Get a Patient object by specifying its id. The response is Patient object .")
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = {
					@Content(schema = @Schema(implementation = Patient.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	@GetMapping("/getPatientBypId/{pId}")
	public ResponseEntity<Patient>getPatientBypId(@PathVariable("pId")int pId) throws PatientNotFoundException{
		Patient patient = patientService.getPatientBypId(pId);
		if(patient==null) {
			throw new PatientNotFoundException("Patient Id does not exists :"+pId);
		}
		return new ResponseEntity<>(patient,HttpStatus.OK);
		
	}
	
	
	
	@Operation(summary = "Retrive All Patient Details", description = "Get all patient detals by sending this request")
	@ApiResponses({
		@ApiResponse(responseCode = "200", content = {
				@Content(schema = @Schema(implementation = Patient.class), mediaType = "application/json") }),
		@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
		@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	 @GetMapping("/getAllPatient") 
	  public ResponseEntity<List<Patient>>getAllPatient(){
	 List<Patient>patient = patientService.getAllPatient();
	  return new ResponseEntity<List<Patient>>(patient,HttpStatus.OK);
	 
	 }
	
	
	@Operation(summary = "Delete patient by Patient Id", description = "Delete patient by the patient Id")
	@ApiResponses({
		@ApiResponse(responseCode = "202",content= {
				@Content(schema=@Schema(implementation = Patient.class),mediaType="application/json")}),
		@ApiResponse(responseCode = "404", content = {
				@Content(schema = @Schema())}),
		@ApiResponse(responseCode = "500", content = {
				@Content(schema=@Schema())
		})
	})
	 @DeleteMapping("/deletePatientByPId/{pId}")
	 public ResponseEntity<String>deletePatientByPId(@PathVariable("pId")int pId) throws PatientNotFoundException{
		 patientService.deletePatientByPId(pId);
		return new ResponseEntity<>("Patient Record Deleted  :"+pId,HttpStatus.ACCEPTED);
		 
	 }
	
	@Operation(summary = "Update patient by Patient Id using Parameter patientId", description = "Update user info using patient Id")
	@ApiResponses({
		@ApiResponse(responseCode = "200",content= {
				@Content(schema=@Schema(implementation = Patient.class),mediaType="application/json")}),
		@ApiResponse(responseCode = "404", content = {
				@Content(schema = @Schema())}),
		@ApiResponse(responseCode = "500", content = {
				@Content(schema=@Schema())
		})
	})
	 @PutMapping("/editPatient")
	 public ResponseEntity<Patient>editPatient(@RequestBody @Valid PatientRequest patientRequest) throws PatientNotFoundException{
		 Patient patient =  patientService.editPatient(patientRequest);
		return new ResponseEntity<Patient>(patient,HttpStatus.OK);
		 
	 }
	
	@Operation(summary = "Update patient by Patient Id using Parameter patientId", description = "Update user info using patient Id")
	@ApiResponses({
		@ApiResponse(responseCode = "200",content= {
				@Content(schema=@Schema(implementation = Patient.class),mediaType="application/json")}),
		@ApiResponse(responseCode = "404", content = {
				@Content(schema = @Schema())}),
		@ApiResponse(responseCode = "500", content = {
				@Content(schema=@Schema())
		})
	})
	 @GetMapping("/findByFirstNameAndAge")
	  public ResponseEntity<List<Patient>>findByFirstNameAndAge(@RequestParam("firstName")String firstName,@RequestParam("age")int age) throws PatientNotFoundException{
		  List<Patient>list = new ArrayList<>();
		  list = patientService.findByFirstNameAndAge(firstName,age);
		  if(list.isEmpty())
			  throw new PatientNotFoundException("Patient Record Not Found......");
		  return new ResponseEntity<List<Patient>>(list,HttpStatus.OK);
		  
	 } 
	
	@Operation(
		    //System.out.print("false");
			summary = "Find Patient Email And Mobile Number by Id",
			description = "Get a Patient object by specifying its id. The response is Patient object ."
			)
	@ApiResponses({
			@ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Patient.class), mediaType = "application/json") }),
			@ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
			@ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	 @GetMapping("getPatientEmailAndMobileUsingId/{pId}")
		public ResponseEntity<Map<String,String>>getPatientDetails(@PathVariable int pId) throws PatientNotFoundException{
			try {
				Patient patient = patientService.getPatientById(pId);
				Map<String ,String > res = new HashMap<>();
				res.put("email", patient.getEmail());
				res.put("mobile", patient.getMobile());
				return ResponseEntity.ok(res);
			}catch(Exception e) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body(Collections.singletonMap("error","Patient Id does not exists....."+pId));
			}
	 }
}




	 
		 
	 

		 
	 
	
