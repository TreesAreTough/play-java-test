package models;

import javax.persistence.*;
import javax.persistence.Id;

@Entity
public class Patient
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PATIENT_ID")
    public Long patientId;

    public String getFirstName()
    {
        return firstName;
    }

    @Column(name = "FIRST_NAME")
    public String firstName;

    @Column(name = "LAST_NAME")
    public String lastName;

    @Column(name = "DOB")
    public String dob;

    @Column(name = "GENDER")
    public String gender;

    @Column(name = "CITY")
    public String city;

    @Column(name = "STATE")
    public String state;

    @Column(name = "ADDRESS")
    public String street;

    @Column(name = "CELL_PHONE")
    public String cell;

    @Column(name = "ZIP")
    public String zipcode;

    @Column(name = "EMAIL")
    public String email;
}
