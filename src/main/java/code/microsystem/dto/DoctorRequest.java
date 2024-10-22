package code.microsystem.dto;

import java.time.LocalDate;

import code.microsystem.entity.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DoctorRequest {
	private int dId;
	private Address address;
	@NotNull(message="firstName can not be null")
	@NotBlank(message = "Doctor First Name Should Not be Blank")
	private String firstName;
	@NotNull(message="middleName can not be null")
	private String middleName;
	@NotNull(message = "Last name can not be null")
	private String lastName;
	@NotNull(message = "Gender Can not be Null")
	@NotBlank(message = "Gender can not be blank")
	@Pattern(regexp = "Male|Female|Other", message = "Gender must be Male, Female, or Other")
	private String gender;
	@NotNull(message = "Email can not be Null")
	@Email(message = "Email is invalid")
	private String email;
	@NotNull(message = "Mobile Number can not be null")
	@NotBlank(message = "Mobile Number should not be blank")
	@Pattern(regexp="(^$|[0-9]{10})",message = "Mobile Number should be 10 digit long")
	private String mobile;
	@NotNull(message = "AadharNumber can not be null")
	private String aadharNumber;
	@NotNull(message = "Date Of Birth can not be Null")
	private LocalDate dob;
	@NotNull(message = "age can not be null")
	@Min(value = 0, message = "Age must be at least 0")
    @Max(value = 120, message = "Age must be less than or equal to 120")
	private int age;
	@NotNull(message = "Department can not be null")
	private String department;
	@NotNull(message = "Specialization can not be null")
	private String specialization;
	@NotNull(message = "Qualification can not be null")
	private String qualification;

}
