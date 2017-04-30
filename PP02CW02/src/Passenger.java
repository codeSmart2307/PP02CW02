
/**
 *
 * @author Raneesh Gomez
 */
public class Passenger {

    private String firstName; // Variable which holds the passenger's first name
    private String surname; // Variable which holds the passenger's last name
    private int secondsInQueue; // Variable which holds the processing delay

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the secondsInQueue
     */
    public int getSecondsInQueue() {
        return secondsInQueue;
    }

    /**
     * @param secondsInQueue the secondsInQueue to set
     */
    public void setSecondsInQueue(int secondsInQueue) {
        this.secondsInQueue = secondsInQueue;
    }

    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getSurname(); // toString() method is called everytime a Passenger object is to be printed to screen or stored in a file
    }

}
