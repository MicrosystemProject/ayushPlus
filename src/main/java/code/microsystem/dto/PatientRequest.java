package code.microsystem.dto;

import java.time.LocalDate;

import code.microsystem.entity.Address;
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
@AllArgsConstructor
@NoArgsConstructor
public class PatientRequest {
	private int pId;
	
	@NotNull(message = "Address cannot be null")
	private Address address;
	@NotNull(message= "Null Value not allow in patient FirstName")
	@NotBlank(message= "Patient First Name Should Not be Blank")
	private String firstName;
	@NotBlank(message= "Patient Middle Name Should Not be Blank")
	private String middleName;
	@NotBlank(message= "Patient Middle Name Should Not be Blank")
	private String lastName;
    @NotBlank(message = "Gender cannot be blank")
    @Pattern(regexp = "Male|Female|Other", message = "Gender must be Male, Female, or Other")
	private String gender;
    @NotBlank(message = "Email should Not be a Blank")
    @Email(message="Email is Invalid")
	private String email;
    @NotBlank(message = "Patient Mobile should Not be Blank")
	@Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Nuber should be 10 digit long")
	private String mobile;
    @NotBlank(message = "Aadhar Number cannot be null")
	private String aadharNumber;
    @NotBlank(message = "Date of Birth cannot be null")
    @Past(message = "Date of Birth must be in the past")
	private LocalDate dob;
    @NotBlank(message = "Age cannot be null")
    @Min(value = 0, message = "Age must be at least 0")
    @Max(value = 120, message = "Age must be less than or equal to 120")
	private int age;



	
 


}


