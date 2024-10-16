package code.microsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import code.microsystem.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

	Object findBypId(int pId);

	@Query("select p from Patient p where p.firstName=?1 and p.age=?2")
	List<Patient> findByFirstNameAndAge(String firstName, int age);

}
