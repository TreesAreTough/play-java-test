package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;

@Entity
public class SuperJoin
{

    @Id
    @Column(name = "First_Name")
    public String firstName;

    @Column(name = "Cell_Phone")
    public String cellPhone;

    @Column(name = "name")
    public String name;

    @Column(name = "dosage")
    public String dosage;

    @Column(name = "Start_Time")
    public Time start_time;

    @Column(name = "frequency")
    public Time frequency;

}
