
/**
 *
 * @author Raneesh Gomez
 */
public class PassengerQueue {

    private static int first = 0; // Queue pointer to front of queue
    private static int last = 0; // Queue pointer to the rear of queue
    public static int passengerCount = 0; // Variable to keep track of number of passengers currently in the queue   
    private static int maxSize = 0; // Variable to hold the maximum size of the array for the simulation report
    private static final int SIZE = 20; // Size of Queue     
    private static Passenger[] queueArray = new Passenger[SIZE]; // Queue Array of Passenger object
    private static Passenger removedPassenger = new Passenger(); // New Passenger object to hold details of passenger that is being removed

    public static void add(Passenger obj) {
        if (!isFull()) {
            queueArray[last] = obj; // The new passenger is added to the rear of the queue
            last = (last + 1) % SIZE;
            passengerCount++; // Number of passengers in the queue is updated
            System.out.println(obj + " has been added to passengerQueue");
        } else {
            System.err.println("Queue is Full! Please wait until there is a slot available"); // Error message is printed if the queue is full
        }
    }

    public static void remove() {
        if (!isEmpty()) {
            removedPassenger = queueArray[first]; // The passenger about to be removed is saved in a new Passenger object so that a message can be printed to screen
            queueArray[first] = null; // Updating object status of passenger in the front of the queue to null indicating departure from queue
            first = (first + 1) % SIZE; // Queue pointer is updated to the next person in line as the front of the queue
            passengerCount--; // Number of passengers in the queue is updated
            System.out.println(removedPassenger + " has been removed from passengerQueue\n");
        } else {
            System.err.println("Queue is Empty! Please move passengers into passengerQueue"); // Error message is printed if the queue is empty
        }
    }

    public static void display() {
        /* If the queue is not empty, the queue iterates through a for loop and checks whether each element has been updated to null.
           If not then that queue element (passenger) is printed to screen*/
        if (!isEmpty()) {
            for (int i = 0; i < SIZE; i++) {
                if (queueArray[i] != null) {
                    System.out.println("Queue Index: " + i + " | " + queueArray[i]);
                }
            }
        } else {
            System.err.println("Queue is Empty! Please move passengers into passengerQueue");
        }
    }

    public static int getMaxSize() {
        maxSize = passengerCount;
        return maxSize; // The number of passengers will always be the greatest in the last iteration. This method prints that amount        
    }

    public static boolean isEmpty() {
        return (passengerCount == 0); // If the number of passengers is 0 then the queue is empty
    }

    public static boolean isFull() {
        return (passengerCount == SIZE); // If the number of passengers in the queue is equal to the size of the queue then the queue is full
    }

    /**
     * @param aFirst the first to set
     */
    public static void setFirst(int aFirst) {
        first = aFirst;
    }

    /**
     * @param aLast the last to set
     */
    public static void setLast(int aLast) {
        last = aLast;
    }

    /**
     * @param aPassengerCount the passengerCount to set
     */
    public static void setPassengerCount(int aPassengerCount) {
        passengerCount = aPassengerCount;
    }

    /**
     * @return the SIZE
     */
    public static int getSIZE() {
        return SIZE;
    }

    /**
     * @return the queueArray
     */
    public static Passenger[] getQueueArray() {
        return queueArray;
    }

}
