package controllers;

import akka.util.ByteString;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Content;
import org.w3c.dom.Document;
import play.Logger;
import play.mvc.Controller;
import play.mvc.Result;


import java.net.HttpURLConnection;
import java.net.URL;


public class XMLController extends Controller
{

    public Result getXML()
    {

        Document doc = null;
        try
        {
            String myURL = "https://wsearch.nlm.nih.gov/ws/query?db=healthTopics&term=depression";

            URL url = new URL(myURL);

            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            doc = play.libs.XML.fromInputStream(request.getInputStream() ,null);

            System.out.println("Name of Child node: "+doc.getFirstChild().getChildNodes().item(13).getChildNodes().item(1).getChildNodes().item(13).getTextContent());


        } catch (Exception e)
        {
            Logger.error("oh no! got some exception: " + e.getMessage());
        }
        return ok(views.html.XML.render(doc.getFirstChild().getChildNodes().item(13).getChildNodes().item(1).getChildNodes().item(13).getTextContent()));

    }


}