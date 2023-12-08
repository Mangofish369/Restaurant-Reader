
/**
 * Write a description of class Customer here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Customer
{
    //Instance variables
    private String firstName;
    private String lastName;
    private int partySize;
    private String specialRequest;

    /**
     * Constructor for objects of class Customer
     */
    public Customer(String firstName, String lastName, int partySize, String specialRequest)
    {
        // initialise instance variables
        this.firstName = firstName;
        this.lastName = lastName;
        this.partySize = partySize;
        this.specialRequest = specialRequest;
    }
    
    public String toString(){
        return firstName + " "+ lastName + ", " + partySize + ", "+ specialRequest;
    }
}
