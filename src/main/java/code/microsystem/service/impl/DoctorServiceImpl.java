package code.microsystem.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import code.microsystem.dto.DoctorRequest;
import code.microsystem.entity.Doctor;
import code.microsystem.exception.DoctorNotFoundException;
import code.microsystem.repository.DoctorRepository;
import code.microsystem.service.DoctorService;
import jakarta.validation.Valid;

@Service
public class DoctorServiceImpl implements DoctorService {

	@Autowired
	private DoctorRepository doctorRepository;

	@Override
	public Doctor addDoctor(DoctorRequest doctorRequest) {
		Doctor doctorObj = Doctor.builder().firstName(doctorRequest.getFirstName())
				.middleName(doctorRequest.getMiddleName()).lastName(doctorRequest.getLastName())
				.email(doctorRequest.getEmail()).gender(doctorRequest.getGender()).mobile(doctorRequest.getMobile())
				.aadharNumber(doctorRequest.getAadharNumber()).age(doctorRequest.getAge()).dob(doctorRequest.getDob())
				.department(doctorRequest.getDepartment()).qualification(doctorRequest.getQualification())
				.specialization(doctorRequest.getSpecialization()).address(doctorRequest.getAddress()).build();
		return doctorRepository.save(doctorObj);
	}

	@Override
	public List<Doctor> addDoctorList(@Valid List<Doctor> doctor) {

		return doctorRepository.saveAll(doctor);
	}

	@Override
	public Doctor getDoctorBydId(int dId) throws DoctorNotFoundException {
		Optional<Doctor> doctorOptional = doctorRepository.findById(dId);
		if (!doctorOptional.isPresent())
			throw new DoctorNotFoundException("Doctor Id Does Not Exists :" + dId);
		return doctorOptional.get();
	}

	@Override
	public List<Doctor> getAllDoctor() {

		return doctorRepository.findAll();
	}

	@Override
	public void deleteDoctorBydId(int dId) throws DoctorNotFoundException {
		Optional<Doctor> doctorOptional = doctorRepository.findById(dId);
		if (!doctorOptional.isPresent())
			throw new DoctorNotFoundException("Doctor id Does not exist :" + dId);
		doctorRepository.deleteById(dId);
	}

	@Override
	public List<Doctor> findByFirstNameAndAge(String firstName, int age) {
		List<Doctor> list = doctorRepository.findByFirstNameAndAge(firstName, age);
		return list;
	}

	@Override
	public Doctor getDoctorById(int dId) throws DoctorNotFoundException {
		return doctorRepository.findById(dId)
				.orElseThrow(() -> new DoctorNotFoundException("Doctor Not Found With Id: " + dId));

	}

	@Override
	public Doctor editDoctor(DoctorRequest doctorRequest) throws DoctorNotFoundException {
		if (doctorRequest == null) {
			throw new IllegalArgumentException("DoctorRequest cannot be null");
		}

		Optional<Doctor> optionalDoctor = doctorRepository.findById(doctorRequest.getDId());
		Doctor doctor = optionalDoctor.get();
		if (doctor == null) {
			throw new DoctorNotFoundException("Doctor not found" + doctorRequest.getDId());
		}
		doctor.setFirstName(doctorRequest.getFirstName());
		doctor.setMiddleName(doctorRequest.getMiddleName());
		doctor.setLastName(doctorRequest.getLastName());
		doctor.setEmail(doctorRequest.getEmail());
		doctor.setGender(doctorRequest.getGender());
		doctor.setAadharNumber(doctorRequest.getAadharNumber());
		doctor.setMobile(doctorRequest.getMobile());
		doctor.setDob(doctorRequest.getDob());
		doctor.setQualification(doctorRequest.getQualification());
		doctor.setDepartment(doctorRequest.getDepartment());
		doctor.setSpecialization(doctorRequest.getSpecialization());
		doctor.setAddress(doctorRequest.getAddress());
		return doctorRepository.save(doctor);
	}

}
