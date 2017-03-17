package daemon;



import controllers.routes;
import play.db.jpa.JPAApi;



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

        routes.DatabaseController.getSuperJoin();
        System.out.println(runNumber++);
    }
}
