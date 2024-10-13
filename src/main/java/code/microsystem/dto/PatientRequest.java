package code.microsystem.dto;

import java.time.LocalDate;

import code.microsystem.entity.Address;
//import code.microsystem.entity.Patient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
//@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest {
	private int pId;
	
	@NotNull(message = "Address cannot be null")
	private Address address;
	
	
	@NotNull(message= "Null Value not allow in patient FirstName")
	@NotBlank(message= "Patient First Name Should Not be Blank")
	private String firstName;
	
	@NotNull(message= "Null Value not allow in patient Middle Name")
	@NotBlank(message= "Patient Middle Name Should Not be Blank")
	private String middleName;
	
	@NotNull(message= "Null Value not allow in patient Middle Name")
	@NotBlank(message= "Patient Middle Name Should Not be Blank")
	private String lastName;
	
	@NotNull(message = "Gender cannot be null")
    @NotBlank(message = "Gender cannot be blank")
    @Pattern(regexp = "Male|Female|Other", message = "Gender must be Male, Female, or Other")
	private String gender;
	
    @NotBlank(message = "Email should Not be a Blank")
    @Email(message="Email is Invalid")
	private String email;
	
    @NotBlank(message = "Patient Mobile should Not be Blank")
	@Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Nuber should be 10 digit long")
	private String mobile;
	
    @NotNull(message = "Aadhar Number cannot be null")
	private String aadharNumber;
	
    @NotNull(message = "Date of Birth cannot be null")
    @Past(message = "Date of Birth must be in the past")
   // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
	private LocalDate dob;
	
    @NotNull(message = "Age cannot be null")
    @Min(value = 0, message = "Age must be at least 0")
    @Max(value = 120, message = "Age must be less than or equal to 120")
	private int age;



	
 


}


