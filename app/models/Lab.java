package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
public class Lab
{
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name="LAB_ID")
    public Long labId;

    public String getLabName()
    {
        return labName;
    }

    @Column (name="LAB_NAME")
    public String labName;

    @Column (name="LAB_DESCRIPTION")
    public String labDescription;

}
