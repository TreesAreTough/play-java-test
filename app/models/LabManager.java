package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.sql.Date;

@Entity
public class LabManager
{

    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="LAB_PULLED_ID")
    public Long labPulledId;

    @Column (name ="DATE_TAKEN")
    public Date dateTaken;

    @Column (name ="PATIENT_ID")
    public Long patientId;

    @Column (name ="LAB_ID")
    public Long labId;

    @Column (name ="DOCTOR_ID")
    public Long doctorId;

    @Column (name="VALUE")
    public String value;

    public String getLabName()
    {
        return labName;
    }

    @Column(name="LAB_NAME")
    public String labName;

    public String getDocName()
    {
        return docName;
    }

    @Column(name="DOC_NAME")
    public String docName;

}
