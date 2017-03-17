package controllers;


import models.*;
import play.data.DynamicForm;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

public class LabManagerController extends Controller
{

    private final FormFactory formFactory;
    private final JPAApi jpaApi;

    @Inject
    public LabManagerController(FormFactory formFactory, JPAApi jpaApi)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    @Transactional(readOnly = true)

    public Result getLabManager()
    {
        List<LabManager> labManagerList = (List<LabManager>) jpaApi.em().createNativeQuery("select lp.lab_pulled_id, lp.date_taken, lp.patient_id, lp.lab_id, lp.doctor_id, lp.value, l.lab_name, p.first_name, d.doc_name from lab_pulled lp\n" +
                "join lab l on lp.lab_id = l.LAB_ID\n" +
                "join patient p on p.patient_id = lp.patient_id\n" +
                "join doctor d on lp.doctor_id = d.doctor_id\n" +
                "order by l.lab_name", LabManager.class).getResultList();

        return ok(views.html.labManager.render(labManagerList));
    }

    @Transactional
    public Result deleteLab(Long labPulledId)
    {
        Lab_Pulled deleteLab = (Lab_Pulled) jpaApi.em().createQuery("select lp from Lab_Pulled lp where lab_pulled_id = :Id", Lab_Pulled.class).setParameter("Id", labPulledId).getSingleResult();
        jpaApi.em().remove(deleteLab);
        return redirect(routes.LabManagerController.getLabManager());
    }

   @Transactional(readOnly = true)
    public Result newLab()
    {

        List<Lab> labList = (List<Lab>) jpaApi.em().createNativeQuery("select * from lab l", Lab.class).getResultList();

        List<Doctor> doctorList = (List<Doctor>) jpaApi.em().createNativeQuery("select * from doctor d", Doctor.class).getResultList();

        List<Patient> patientList =(List<Patient>) jpaApi.em().createNativeQuery("select * from patient p", Patient.class).getResultList();




        return ok(views.html.newLab.render(labList,doctorList, patientList));
    }

    @Transactional
    public Result addLab()
    {

        Lab_Pulled lab = formFactory.form(Lab_Pulled.class).bindFromRequest().get();
        lab.dateTaken = LocalDate.now();
        jpaApi.em().persist(lab);
        return redirect(routes.LabManagerController.getLabManager());
    }

    @Transactional
    public Result editLab(Long labPulledId)
    {
        SecondSuperJoin currentLab = (SecondSuperJoin) jpaApi.em().createNativeQuery("select l.lab_name, lp.lab_id, lp.lab_pulled_Id, lp.date_taken, lp.patient_Id, lp.doctor_id, lp.value, p.first_name, d.doc_name from lab_Pulled lp\n" +
                "join lab l on l.LAB_ID = lp.lab_id\n" +
                "join patient p on lp.patient_id = p.PATIENT_ID\n" +
                "join doctor d on lp.doctor_id = d.DOCTOR_ID  where lp.lab_pulled_id = :Id", SecondSuperJoin.class).setParameter("Id", labPulledId).getSingleResult();

        List<Lab> labList = (List<Lab>) jpaApi.em().createQuery("select l from Lab l", Lab.class).getResultList();

        List<Doctor> doctorList = (List<Doctor>) jpaApi.em().createQuery("select d from Doctor d", Doctor.class).getResultList();

        List<Patient> patientList = (List<Patient>) jpaApi.em().createQuery("select p from Patient p", Patient.class).getResultList();

        List<Lab_Pulled> labPulledList =(List<Lab_Pulled>) jpaApi.em().createQuery("select lp from Lab_Pulled lp", Lab_Pulled.class).getResultList();

        return ok(views.html.editLab.render(currentLab,labList,doctorList,patientList,labPulledList));
    }

    @Transactional
    public Result updateLab()
    {
        DynamicForm postedForm = formFactory.form().bindFromRequest();
        Long labPulledId = new Long(postedForm.get("labPulledId"));
        Long doctorId = new Long(postedForm.get("doctorId"));
        String value = postedForm.get("value");

        LocalDate dateTaken = LocalDate.parse(postedForm.get("labDate"));

        Lab_Pulled lab_pulled = (Lab_Pulled) jpaApi.em().createQuery("select lp from Lab_Pulled lp where lp.labPulledId = :Id").setParameter("Id", labPulledId).getSingleResult();


        lab_pulled.value = value;
        lab_pulled.doctorId=doctorId;
        lab_pulled.labPulledId=labPulledId;
        lab_pulled.dateTaken=dateTaken;

        jpaApi.em().persist(lab_pulled);

        return redirect(routes.LabManagerController.getLabManager());
    }
}
