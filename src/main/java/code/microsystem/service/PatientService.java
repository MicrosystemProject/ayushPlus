package code.microsystem.service;

import java.util.List;

import code.microsystem.dto.PatientRequest;
import code.microsystem.entity.Patient;
import code.microsystem.exception.PatientNotFoundException;
import jakarta.validation.Valid;

public interface PatientService {

	Patient addPatient(PatientRequest patientRequest);

	List<Patient> addPatientList(@Valid List<Patient> patients);

	Patient getPatientBypId(int pId) throws PatientNotFoundException;

	List<Patient> getAllPatient();

	void deletePatientByPId(int pId) throws PatientNotFoundException;

	Patient editPatient(PatientRequest patientRequest) throws PatientNotFoundException;

	List<Patient> findByFirstNameAndAge(String firstName, int age);

	Patient getPatientById(int pId) throws PatientNotFoundException;


}
