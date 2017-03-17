package models;

import javax.persistence.*;
import javax.persistence.Id;

@Entity
public class Person
{

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	public Long id;

    public String name;
}
