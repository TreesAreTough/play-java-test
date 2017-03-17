package controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import models.Patient;
import models.RandomUser;
import play.Logger;
import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


import static play.libs.Json.toJson;

public class UserController extends Controller
{

    private final FormFactory formFactory;
    private final JPAApi jpaApi;

    @Inject
    public UserController(FormFactory formFactory, JPAApi jpaApi) {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }

    @Transactional (readOnly = true)
    public Result index()
    {
        List<Patient> patient = (List<Patient>) jpaApi.em().createQuery("Select p from Patient p").getResultList();

        return ok(views.html.user.render(patient));
    }


    @Transactional(readOnly = true)
    public Result getUsers()
    {
        List<Patient> patient = (List<Patient>) jpaApi.em().createQuery("select lastName, firstName from Patient").getResultList();
        return ok(toJson(patient));
    }

    @Transactional
    public Result addUser()
    {

        Patient patient = formFactory.form(Patient.class).bindFromRequest().get();

        jpaApi.em().persist(patient);

        return redirect(routes.UserController.index());
    }


    public Result newUser()
    {
        RandomUser randomUser = null;
        try
        {
            String myURL = "https://randomuser.me/api";

            URL url = new URL(myURL);

            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();


            ObjectMapper objectMapper = new ObjectMapper();
            randomUser = objectMapper.readerFor(RandomUser.class).readValue(url);
        } catch (Exception e)
        {
            Logger.error("oh no! got some exception: " + e.getMessage());
        }

        return ok(views.html.newUser.render(randomUser));
    }

}
