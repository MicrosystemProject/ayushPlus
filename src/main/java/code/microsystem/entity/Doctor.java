package code.microsystem.entity;

import java.time.LocalDate;
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
	private String firstName;
	private String middleName;
	private String lastName;
	private String gender;
	private String email;
	private String mobile;
	@Column(unique = true, nullable = false,length = 12)
	private String aadharNumber;
	private LocalDate dob;
	private int age;
	
public void setDob(LocalDate dob) {
        this.dob = dob;
   calculateAge();   
 }
   private void calculateAge() {
       if (dob != null) {
           this.age = Period.between(dob, LocalDate.now()).getYears();
     } else {
           this.age = 0; 
   }
  }

   @PrePersist
  @PreUpdate
public void updateAgeBeforeSave() {
	   calculateAge(); 
   }
	private String department;
	private String specialization;
	private String qualification;
	public Doctor orElseThrow(Object object) {
		return null;
	}

}

