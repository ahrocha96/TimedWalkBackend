package com.example.demo.Patient;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import com.example.demo.Patient.IPatientSchema;
import com.example.demo.Patient.Patient;
import com.example.demo.Patient.PatientSchema;
import com.example.demo.config.CustomMongoDbConfig;

@RunWith(MockitoJUnitRunner.class)
public class PatientSchemaTest {

	private IPatientSchema patientSchema = new PatientSchema(CustomMongoDbConfig.mongoTemplate());
	
//-------------------Before each test clear the schema------------------------------

	@Before
	public void setup() {
		
		patientSchema.deleteAll();
		
	}
//-------------------Save and Update Tests------------------------------
	
	@Test
	public void SaveNewPatientShouldReturnTrue() {
		
		Patient patient = new Patient();
		
		patient.setAverageTimeTaken(10);
		patient.setFirstName("Andrew");
		patient.setLastName("Rocha");
		patient.setGaitSpeed(1);
		patient.setNote("This has been a success");
		patient.setPatientID(patientSchema.getTopID());
		patientSchema.save(patient);
				
		int topID = patientSchema.getTopID()-1;
		assertTrue(patientSchema.findByPatientID(topID).getFirstName().equals("Andrew"));
	}
	

	@Test
	public void UpdateExistingPatientShouldReturnTrue(){
		
		Patient patient = new Patient();
		
		patient.setAverageTimeTaken(10);
		patient.setFirstName("Andrew");
		patient.setLastName("Rocha");
		patient.setGaitSpeed(1);
		patient.setNote("This has been a success");
		patient.setPatientID(patientSchema.getTopID());
		patientSchema.save(patient);	
		
		int topID = patientSchema.getTopID()-1;

		patient.setAverageTimeTaken(50);
		patient.setFirstName("Jim");
		patient.setLastName("Jerry");
		patient.setGaitSpeed(100);
		patient.setNote("This has been a GREAT success");
		patient.setPatientID(topID);
		patientSchema.update(patient);
				
		assertTrue(patientSchema.findByPatientID(topID).getGaitSpeed() == 100 && patientSchema.findByPatientID(topID).getFirstName().equals("Andrew"));
	}
	
	@Test
	public void UpdateNonExistingPatientShouldReturnFalse(){
		Patient patient = new Patient();
		
		patient.setAverageTimeTaken(50);
		patient.setFirstName("Jim");
		patient.setLastName("Jerry");
		patient.setGaitSpeed(100);
		patient.setNote("This has been a GREAT success");
		int topID = patientSchema.getTopID();
		patient.setPatientID(topID+2);
		patientSchema.update(patient);
				
		assertTrue((patientSchema.findByPatientID(topID) != null) == false);
	}
	
	
	@Test
	public void FindNonExistingPatientShouldReturnFalse(){
		assertTrue((patientSchema.findByPatientID(100) != null) == false);
	}
	
	@Test
	public void RemovePatientShouldReturnTrue(){

		Patient patient = new Patient();
		
		patient.setAverageTimeTaken(10);
		patient.setFirstName("Andrew");
		patient.setLastName("Rocha");
		patient.setGaitSpeed(1);
		patient.setNote("This has been a success");
		patient.setPatientID(patientSchema.getTopID());
		
		patientSchema.save(patient);
		patientSchema.remove(1);
		
		assertTrue(patientSchema.findByPatientID(1) == null);
	}
	
	@Test
	public void FindAllPatientsShouldReturnTrue(){
		Patient patient = new Patient();
		Patient patient2 = new Patient();
		Patient patient3 = new Patient();
		List<Patient> patientList;
		
		patient.setAverageTimeTaken(10);
		patient.setFirstName("Andrew");
		patient.setLastName("Rocha");
		patient.setGaitSpeed(1);
		patient.setNote("This has been a success");
		patient.setPatientID(patientSchema.getTopID());
		patientSchema.save(patient);

		
		patient2.setAverageTimeTaken(10);
		patient2.setFirstName("Jim");
		patient2.setLastName("Jones");
		patient2.setGaitSpeed(1);
		patient2.setNote("This has also been a success");
		patient2.setPatientID(patientSchema.getTopID());
		patientSchema.save(patient2);
		
		
		
		patient3.setAverageTimeTaken(10);
		patient3.setFirstName("Mary");
		patient3.setLastName("Smith");
		patient3.setGaitSpeed(1);
		patient3.setNote("This has also also been a success");
		patient3.setPatientID(patientSchema.getTopID());
		patientSchema.save(patient3);
		
		patientList = patientSchema.findAllPatients();
		
		assertTrue(patientList.size() == 3);
	}
	
}
