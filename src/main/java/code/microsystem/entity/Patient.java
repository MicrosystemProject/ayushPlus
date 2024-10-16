package code.microsystem.entity;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.ManyToOne;
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
@Table(name = "patient_Details")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class Patient extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int pId;

	
	@OneToOne(cascade = CascadeType.ALL)
	//@JoinColumn(name = "address_id")
	private Address address;

	
	private String firstName;

	
	private String middleName;

	
	private String lastName;

	
	private String gender;

	
	private String email;

	
	private String mobile;

	@Column(unique = true, nullable = false, length = 12)
	private String aadharNumber;

	@Column(name = "patient_DOB")
	private LocalDate dob;

	@Column(name = "patient_age")
	private int age;

	 //Setter for dob with age calculation
	
	  public void setDob(LocalDate dob) { 
		  this.dob = dob; calculateAge(); 
	  }
	 
	 private void calculateAge() { 
		 if (dob != null) { 
     this.age = Period.between(dob, LocalDate.now()).getYears(); 
     }  else { 
		this.age = 0;
     }
	 }
	 
	 @PrePersist
	  
	 @PreUpdate public void updateAgeBeforeSave() { 
		 calculateAge(); // Recalculaage before saving or updating the entity 
	 }
	 
}
