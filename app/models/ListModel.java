package models;

public class ListModel
{
    private String num;

    private Document[] document;

    private String per;

    private String start;

    public String getNum ()
    {
        return num;
    }

    public void setNum (String num)
    {
        this.num = num;
    }

    public Document[] getDocument ()
    {
        return document;
    }

    public void setDocument (Document[] document)
    {
        this.document = document;
    }

    public String getPer ()
    {
        return per;
    }

    public void setPer (String per)
    {
        this.per = per;
    }

    public String getStart ()
    {
        return start;
    }

    public void setStart (String start)
    {
        this.start = start;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [num = "+num+", document = "+document+", per = "+per+", start = "+start+"]";
    }
}