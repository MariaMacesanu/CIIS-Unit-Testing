public class Appointment
{
    private Appointment app;
    private String username;
    private String serviceType;
    private String day;
    private String time;
    private boolean flexible;

    public Appointment(String u, String t)
    {
        username = u;
        serviceType = t;
    }

    public Appointment(String u, String ty, String d, String ti, boolean f)
    {
        username = u;
        serviceType = ty;
        day = d;
        time = ti;
        flexible = f;
        
    }

    public String getUsername()
    { return username; }

    public String getServiceType()
    { return serviceType; }

    public String getDay()
    { return day; }

    public String getTime()
    { return time; }

    public boolean getFlexibility()
    { return flexible; }

    public void setDay(String d)
    { day = d; }

    public void setTime(String t)
    { time = t; }

    public String toString()
    { return username + ": " + serviceType + " on " + day + " at " + time; }
}