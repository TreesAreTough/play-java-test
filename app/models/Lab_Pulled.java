package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.time.LocalDate;

@Entity
public class Lab_Pulled
{
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name ="LAB_PULLED_ID")
    public Long labPulledId;

    @Column (name ="DATE_TAKEN")
    public LocalDate dateTaken;

    @Column (name ="PATIENT_ID")
    public Long patientId;

    @Column (name ="LAB_ID")
    public Long labId;

    @Column (name ="DOCTOR_ID")
    public Long doctorId;

    @Column (name="VALUE")
    public String value;
}
