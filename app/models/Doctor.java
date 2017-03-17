package models;

import javax.persistence.*;

@Entity
public class Doctor
{

    public String getDoctorId()
    {
        return doctorId;
    }

    @javax.persistence.Id
    @Column (name ="DOCTOR_ID")
    public String doctorId;

    public String getAddress()
    {
        return docAddress;
    }

    @Column (name = "DOC_ADDRESS")
    public String docAddress;

    public String getCity()
    {
        return docCity;
    }

    @Column (name = "DOC_CITY")
    public String docCity;

    public String getState()
    {
        return docState;
    }

    @Column (name = "DOC_STATE")
    public String docState;

    public String getZip()
    {
        return docZip;
    }

    @Column (name = "DOC_ZIP")
    public String docZip;

    public String getDoctorName()
    {
        return docName;
    }

    @Column (name ="DOC_NAME")
    public String docName;


}
