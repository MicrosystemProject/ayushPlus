package code.microsystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code.microsystem.dto.PatientRequest;
import code.microsystem.entity.Patient;
import code.microsystem.exception.PatientNotFoundException;
import code.microsystem.repository.PatientRepository;
import code.microsystem.service.PatientService;
import jakarta.validation.Valid;


@Service
public class PatientServiceImpl implements PatientService {
	
	@Autowired
	private PatientRepository patientRepository;

	@Override
	public Patient addPatient(PatientRequest patientRequest) {
		Patient patientObj = Patient.builder().firstName(patientRequest.getFirstName()).middleName(patientRequest.getMiddleName()).
				lastName(patientRequest.getLastName()).age(patientRequest.getAge()).dob(patientRequest.getDob()).
                gender(patientRequest.getGender()).email(patientRequest.getEmail()).mobile(patientRequest.getMobile()).
                aadharNumber(patientRequest.getAadharNumber()).address(patientRequest.getAddress()).build();
		return patientRepository.save(patientObj);
	}

	@Override
	public List<Patient> addPatientList(@Valid List<Patient> patients) {
		// TODO Auto-generated method stub
		return patientRepository.saveAll(patients);
	}

	@Override
	public Patient getPatientBypId(int pId) throws PatientNotFoundException {
		Optional<Patient> patientOptional = patientRepository.findById(pId);
		if(!patientOptional.isPresent())
			throw new PatientNotFoundException("Patient Id Does Not exists :" + pId);
		
		return patientOptional.get();
	}

	@Override
	public List<Patient> getAllPatient() {
		
		return patientRepository.findAll();
	}

	@Override
	public void deletePatientByPId(int pId) throws PatientNotFoundException {
		Optional<Patient> patientOptional = patientRepository.findById(pId);
		if(!patientOptional.isPresent())
			throw new PatientNotFoundException("Patient Id Does Not exist :" +pId);
		
		patientRepository.deleteById(pId);
		
	}

	@Override
	public Patient editPatient(PatientRequest patientRequest) throws PatientNotFoundException {
	    Patient patient = patientRepository.findById(patientRequest.getPId())
	            .orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + patientRequest.getPId()));
	    
	    // Update patient fields
	    patient.setFirstName(patientRequest.getFirstName());
	    patient.setMiddleName(patientRequest.getMiddleName());
	    patient.setLastName(patientRequest.getLastName());
	    patient.setGender(patientRequest.getGender());
	    patient.setEmail(patientRequest.getEmail());
	    patient.setMobile(patientRequest.getMobile());
	    patient.setAadharNumber(patientRequest.getAadharNumber());
	    patient.setDob(patientRequest.getDob());
	    patient.setAddress(patientRequest.getAddress());

	    // Save the updated patient
	    return patientRepository.save(patient);
	}

	@Override
	public List<Patient> findByFirstNameAndAge(String firstName, int age) {
		List<Patient>list = patientRepository.findByFirstNameAndAge(firstName,age);
		return list;
	}

	

	@Override
	public Patient getPatientById(int pId) throws PatientNotFoundException {
		 return patientRepository.findById(pId).orElseThrow(() -> new PatientNotFoundException("Patient not found with ID: " + pId));
		
	}
	

}
