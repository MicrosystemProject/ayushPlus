package code.microsystem.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.LastModifiedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "patients_history")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "patient_history_patients", joinColumns = @JoinColumn(name = "history_id"), 
	inverseJoinColumns = @JoinColumn(name = "patient_id"))
	private List<Patient> patients;
    
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "patient_history_doctors", joinColumns = @JoinColumn(name = "history_id"), 
	inverseJoinColumns = @JoinColumn(name = "doctor_id"))
	private List<Doctor> doctors;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime createdDate;

	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
	private LocalDateTime lastModifiedDate;
}
