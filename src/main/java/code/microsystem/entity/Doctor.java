package code.microsystem.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="doctor_info")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Doctor extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="dId")
	private int dId;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="address_id")
	private Address address;
	
	//@Column(name="doctort_firstName")
	private String firstName;
	
	//@Column(name="doctort_middleName")
	private String middleName;
	
	//@Column(name="doctor_lastName")
	private String lastName;
	
	//@Column(name="doctor_gender")
	private String gender;
	
	//@Column(name="doctor_email")
	private String email;
	
	//@Column(name="doctor_mobileNo")
	private String mobile;
	
	@Column(unique = true, nullable = false,length = 12)
	private String aadharNumber;

	//@Column(name="doctor_DOB")
	private LocalDate dob;
	
	//@Column(name="doctor_age")
	private int age;
	
public void setDob(LocalDate dob) {
        this.dob = dob;
   calculateAge();   
 }

 
   private void calculateAge() {
       if (dob != null) {
           this.age = Period.between(dob, LocalDate.now()).getYears();
     } else {
           this.age = 0;  // Default if dob is not set
   }
  }

   @PrePersist
  @PreUpdate
public void updateAgeBeforeSave() {
	   calculateAge();  // Recalculate age before saving or updating the entity
   }
   

	
	
	//@Column(name="doctor_department")
	private String department;
	
	//@Column(name="doctor_specialization")
	private String specialization;
	
	//@Column(name="doctor_qualification")
	private String qualification;

	public Doctor orElseThrow(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

}

