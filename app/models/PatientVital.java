package models;


import javax.persistence.*;
import javax.persistence.Id;


@Entity
public class PatientVital
{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PATIENT_VITAL_ID")
    public Long patientVitalID;

    @Column(name = "NAME")
    public String name;

    @Column(name = "VALUE")
    public String value;

    @Column(name = "DATE_TAKEN")
    public java.sql.Date dateTaken;



}
