package models;


import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class SecondSuperJoin
{
    @javax.persistence.Id
    @Column (name ="LAB_PULLED_ID")
    public Integer labPulledId;

    @Column (name ="DOC_NAME")
    public String docName;

    @Column(name = "PATIENT_ID")
    public Long patientId;

    @Column (name ="DATE_TAKEN")
    public LocalDate dateTaken;

    @Column (name ="DOCTOR_ID")
    public Integer doctorId;

    @Column (name="VALUE")
    public String value;

    @Column (name="LAB_ID")
    public Long labId;

    @Column (name="LAB_NAME")
    public String labName;

    @Column (name="FIRST_NAME")
    public String firstName;

}
