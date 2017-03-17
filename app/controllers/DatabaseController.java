package controllers;

import models.SuperJoin;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.List;

import static play.libs.Json.toJson;

public class DatabaseController extends Controller
{

    private final FormFactory formFactory;
    private final JPAApi jpaApi;

    @Inject
    public DatabaseController(FormFactory formFactory, JPAApi jpaApi)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    @Transactional(readOnly = true)
    public Result getSuperJoin()
    {
        List<SuperJoin> superJoin = (List<SuperJoin>) jpaApi.em().createNativeQuery("select p.first_name, p.cell_phone,m.NAME, pre.DOSAGE, r.start_time, f.FREQUENCY from patient p\n" +
                "join Prescription_REMINDER r on p.PATIENT_ID = r.Patient_id \n" +
                "join frequency f on r.frequency_id = f.FREQUENCY_ID\n" +
                "join prescription pre on r.prescription_id = pre.prescription_id\n" +
                "join medication m on pre.MEDICATION_ID = m.medication_id \n", SuperJoin.class).getResultList();
        return ok(toJson(superJoin));
    }

    public Result getPrescription()
    {
        return ok(views.html.prescription.render());
    }



}
