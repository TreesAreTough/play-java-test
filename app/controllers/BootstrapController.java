package controllers;

import models.Doctor;
import models.VitalJoin;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;


public class BootstrapController extends Controller
{
    public Result blah()
    {
        return ok(views.html.blah.render());
    }

    @Transactional
    public Result charts()
    {
        List<VitalJoin> vitalJoins = (List<VitalJoin>) jpaApi.em().createNativeQuery("select x.value val1, y.value val2,x.date_taken from (select pv.PATIENT_VITAL_ID, v.vital_name, pv.value, pv.date_taken from patient p \n" +
                "join patient_vital pv on p.PATIENT_ID = pv.PATIENT_ID\n" +
                "join vitals v on pv.VITAL_ID = v.VITAL_ID where v.vital_NAME = 'weight') x join \n" +
                "(select lp.LAB_PULLED_ID, l.lab_name, lp.value, lp.date_taken from patient p\n" +
                "join lab_pulled lp on p.PATIENT_ID = lp.PATIENT_ID\n" +
                "join lab l on lp.LAB_ID = l.LAB_ID where l.lab_name = 'Total cholesterol') y\n" +
                "on x.date_taken = y.date_taken order by date_taken asc", VitalJoin.class).getResultList();

        List<VitalJoin> vitalJoins2 = (List<VitalJoin>) jpaApi.em().createNativeQuery("select x.value val1, y.value val2,x.date_taken from (select lp.LAB_PULLED_ID, l.lab_name, lp.value, lp.date_taken from patient p \n" +
                "join lab_pulled lp on p.PATIENT_ID = lp.PATIENT_ID\n" +
                "join lab l on lp.LAB_ID = l.LAB_ID where l.lab_name = \"Hemoglobin A1c\") x join\n" +
                "(select lp.LAB_PULLED_ID, l.lab_name, lp.value, lp.date_taken from patient p\n" +
                "join lab_pulled lp on p.PATIENT_ID = lp.PATIENT_ID\n" +
                "join lab l on lp.LAB_ID = l.LAB_ID where l.lab_name = \"Gucose\") y\n" +
                "on x.date_taken = y.date_taken\n" +
                "order by date_taken asc", VitalJoin.class).getResultList();

        return ok(views.html.charts.render(vitalJoins,vitalJoins2));
    }
    public Result alerts()
    {
        return ok(views.html.alerts.render());
    }
    public Result forms()
    {
        return ok(views.html.forms.render());
    }
    public Result indexFinal()
    {
        return ok(views.html.indexFinal.render());
    }
    public Result profile ()
    {
        return ok(views.html.profile.render());
    }


    private final JPAApi jpaApi;

    @Inject
    public BootstrapController(JPAApi jpaApi)
    {
        this.jpaApi = jpaApi;
    }


    @Transactional(readOnly = true)
    public Result getDoctor2()
    {
        String id = request().getQueryString("id");



        if ( id == null)
        {
             id ="1";
        }
        Doctor doctor = (Doctor) jpaApi.em().createNativeQuery("select doctor_id, DOC_NAME, DOC_ADDRESS, DOC_CITY, DOC_STATE, DOC_ZIP from doctor where doctor_id = :id", Doctor.class).setParameter("id", id).getSingleResult();

        List<Doctor> doctors = (List<Doctor>) jpaApi.em().createNativeQuery("select doctor_id, DOC_NAME, DOC_ADDRESS, DOC_CITY, DOC_STATE, DOC_ZIP from doctor", Doctor.class).getResultList();



        return ok(views.html.tables.render(doctors, doctor));
    }


}
