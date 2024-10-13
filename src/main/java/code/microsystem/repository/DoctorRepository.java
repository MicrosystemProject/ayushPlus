package code.microsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import code.microsystem.entity.Doctor;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

	//List<Doctor> findByFirstNameAndAge(String firstName, int age);
	@Query("select d from Doctor d where d.firstName=?1 and d.age=?2")
	List<Doctor>findByFirstNameAndAge(String firstName,int age);

}
