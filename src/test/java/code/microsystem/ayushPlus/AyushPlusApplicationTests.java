package code.microsystem.ayushPlus;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import code.microsystem.dto.PatientRequest;
import code.microsystem.entity.Address;
import code.microsystem.entity.Patient;
import code.microsystem.exception.PatientNotFoundException;
import code.microsystem.repository.PatientRepository;
import code.microsystem.service.PatientService;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)  // JUnit 5 extension
class AyushPlusApplicationTests {

    @Autowired
    private PatientService patientService;
    
    @MockBean
    private PatientRepository patientRepository;

    @Test
    public void testAddPatient() {
    	
       // Create a PatientRequest object
        PatientRequest patientRequest = new PatientRequest();
        patientRequest.setFirstName("Rutuja");
        patientRequest.setMiddleName("Keshav");
        patientRequest.setLastName("Mankar");
        patientRequest.setEmail("rutujamankar@gmail.com");
        patientRequest.setAadharNumber("519401923400");
        patientRequest.setDob(LocalDate.parse("2001-06-20"));
        patientRequest.setAge(23);
        patientRequest.setMobile("8378057517");
        patientRequest.setGender("Female");
        
        // Create an Address object
        Address address = new Address();
        address.setAddressLine1("Nagpur");
        address.setAddressLine2("Pune");
        address.setCountry("Indian");
        address.setCity("Nagpur");
        address.setState("Maharashtra");
        address.setDist("Yavatmal");
        address.setPostalCode("440001");
        
       patientRequest.setAddress(address); // Set the Address object in PatientRequest

        // Create a Patient object to be returned by the service
        Patient  savedPatient = Patient.builder().pId(1).firstName("Suchita").middleName("Sanket").lastName("Sharma").
        		email("suchitasharma@gmail.com")
        		.age(30).gender("Female").mobile("8805449634").aadharNumber("1234536278").address(address).build();

        // Mock the service layer to return the patient when the addPatient method is called
        when(patientRepository.save(any(Patient.class))).thenReturn(savedPatient);

        //When
       Patient result = patientService.addPatient(patientRequest);
       
       //Then 
       assertEquals(savedPatient.getPId(),result.getPId());
       assertEquals(savedPatient.getFirstName(),result.getFirstName());
       assertEquals(savedPatient.getMiddleName(),result.getMiddleName());
       assertEquals(savedPatient.getLastName(),result.getLastName());
       assertEquals(savedPatient.getAadharNumber(),result.getAadharNumber());
       assertEquals(savedPatient.getAge(),result.getAge());
       assertEquals(savedPatient.getDob(),result.getDob());
       assertEquals(savedPatient.getEmail(),result.getEmail());
       assertEquals(savedPatient.getGender(),result.getGender());
       assertEquals(savedPatient.getMobile(),result .getMobile());
       assertEquals(savedPatient.getAddress(),result.getAddress());
   }
    @Test
    public void getAllPatientTest() {
        // Create sample Patient objects
        Patient patient1 = Patient.builder()
                .pId(1)
                .firstName("Rutuja")
                .middleName("Keshav")
                .lastName("Mankar")
                .email("rutujamankar@gmail.com")
                .age(23)
                .gender("Female")
                .mobile("8378057517")
                .aadharNumber("519401923400")
                .build();

        Patient patient2 = Patient.builder()
                .pId(2)
                .firstName("John")
                .middleName("Doe")
                .lastName("Smith")
                .email("johnsmith@gmail.com")
                .age(30)
                .gender("Male")
                .mobile("1234567890")
                .aadharNumber("9876543210")
                .build();

        // Create a list of patients
        List<Patient> patientList = Arrays.asList(patient1, patient2);

        // Mock the repository to return the patient list when the findAll method is called
        when(patientRepository.findAll()).thenReturn(patientList);

        // When
        List<Patient> result = patientService.getAllPatient();

        // Then
        assertEquals(2, result.size()); // Check that the size of the returned list is correct
        assertEquals(patient1.getPId(), result.get(0).getPId());
        assertEquals(patient2.getPId(), result.get(1).getPId());
        assertEquals(patient1.getFirstName(), result.get(0).getFirstName());
        assertEquals(patient2.getFirstName(), result.get(1).getFirstName());
        assertEquals(patient1.getLastName(), result.get(0).getLastName());
        assertEquals(patient2.getLastName(), result.get(1).getLastName());
        // Add more assertions as needed
    }
    
    	
    	@Test
        public void deletePatientByPId_PatientExists() throws PatientNotFoundException {
            // Arrange
            int patientId = 1;
            Patient mockPatient = new Patient();
            mockPatient.setPId(patientId);
            
            // When findById is called, return a mock patient
            when(patientRepository.findById(patientId)).thenReturn(Optional.of(mockPatient));

            // Act
            patientService.deletePatientByPId(patientId);

            // Assert
            verify(patientRepository, times(1)).deleteById(patientId); // Verify that deleteById was called once
        }

        @Test
        public void deletePatientByPId_PatientDoesNotExist() {
            // Arrange
            int patientId = 999; // Non-existing patient ID
            
            // When findById is called, return an empty optional
            when(patientRepository.findById(patientId)).thenReturn(Optional.empty());

            // Act & Assert
            PatientNotFoundException thrown = assertThrows(PatientNotFoundException.class, () -> {
                patientService.deletePatientByPId(patientId);
            });

            assertEquals("Patient Id Does Not exist: " + patientId, thrown.getMessage()); // Assert that the correct exception message is returned
        }
  
    }


