package code.microsystem.service;

import java.util.List;

import code.microsystem.dto.DoctorRequest;
import code.microsystem.entity.Doctor;
import code.microsystem.exception.DoctorNotFoundException;
import jakarta.validation.Valid;


public interface DoctorService {

	Doctor addDoctor(DoctorRequest doctorRequest);

	List<Doctor> addDoctorList(@Valid List<Doctor> doctor);

	Doctor getDoctorBydId(int dId) throws DoctorNotFoundException;

	List<Doctor> getAllDoctor();

	void deleteDoctorBydId(int dId) throws DoctorNotFoundException;

	List<Doctor> findByFirstNameAndAge(String firstName, int age);

	Doctor getDoctorById(int dId) throws DoctorNotFoundException;

	Doctor editDoctor(@Valid DoctorRequest doctorRequest) throws DoctorNotFoundException;

	
}
