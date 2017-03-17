package models;


import play.data.FormFactory;
import play.db.jpa.JPAApi;

import javax.inject.Inject;
import java.util.*;
import java.util.List;

public class Daemon implements Runnable
{

    private JPAApi jpaApi;
    private static int runNumber = 0;

    private static boolean running = false;


    public void setJPAApi(JPAApi jpaApi)
    {
        this.jpaApi = jpaApi;
    }

    public static boolean getRunning()
    {
        return running;
    }

    @Override
    public void run()
    {
        running = true;

        java.util.List<SuperJoin> superJoin = (List<SuperJoin>) jpaApi.em().createNativeQuery("select p.first_name, p.cell_phone,m.NAME, pre.DOSAGE, r.start_time, f.FREQUENCY from patient p\n" +
                "join Prescription_REMINDER r on p.PATIENT_ID = r.Patient_id \n" +
                "join frequency f on r.frequency_id = f.FREQUENCY_ID\n" +
                "join prescription pre on r.prescription_id = pre.prescription_id\n" +
                "join medication m on pre.MEDICATION_ID = m.medication_id \n", SuperJoin.class).getResultList();
        System.out.println(runNumber++);
    }
}
