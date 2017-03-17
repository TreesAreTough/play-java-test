package models;

public class Test
{
    private NlmSearchResult nlmSearchResult;

    public NlmSearchResult getNlmSearchResult ()
    {
        return nlmSearchResult;
    }

    public void setNlmSearchResult (NlmSearchResult nlmSearchResult)
    {
        this.nlmSearchResult = nlmSearchResult;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [nlmSearchResult = "+nlmSearchResult+"]";
    }
}

