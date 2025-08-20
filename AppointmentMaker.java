public class AppointmentMaker
{
    public static void main(String[] args)
    {
        Appointment app = new Appointment("username", "Interview", "Wednesday", "3;00", false);
        System.out.println(app.toString());
    }
}