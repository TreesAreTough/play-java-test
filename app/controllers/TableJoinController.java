package controllers;

import play.data.FormFactory;
import play.db.jpa.JPAApi;
import play.mvc.Controller;

import javax.inject.Inject;

public class TableJoinController extends Controller
{

    private final FormFactory formFactory;
    private final JPAApi jpaApi;

    @Inject
    public TableJoinController(FormFactory formFactory, JPAApi jpaApi)
    {
        this.formFactory = formFactory;
        this.jpaApi = jpaApi;
    }


}
