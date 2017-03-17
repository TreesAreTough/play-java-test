package models;

import javax.persistence.*;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class VitalJoin2
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DATE_TAKEN")
    public Date dateTaken;

    @Column(name = "val1")
    public String val1;

    @Column(name = "val2")
    public String val2;
}
