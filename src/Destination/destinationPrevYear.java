package Destination;

/**
 * Created by jithinjanardanan on 2/7/17.
 */
public class destinationPrevYear
{
    private String dest;
    public destinationPrevYear()
    {}
    public destinationPrevYear(String dest)
    {
        //Checks if the input is blank
        if(dest.trim().length() == 0)
            throw new IllegalStateException("Invalid Destination! Please check the input");
        this.dest = dest;
    }
    public String getDest()
    {
        return this.dest;
    }

}
