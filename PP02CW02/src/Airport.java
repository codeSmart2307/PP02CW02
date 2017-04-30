
/**
 *
 * @author Dinuka Raneesh Anton Gomez
 * @studentID 2016087
 * @uowID 16266986
 * @module PP02
 * @group A
 * @semester 02
 * @coursework# 02
 * @level 04
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Airport {

    private static ArrayList<Passenger> passengers = new ArrayList<Passenger>(); //Declaring an ArrayList of Passenger to store all Passenger details
    private static PassengerQueue queue1 = new PassengerQueue(); //Declaring a PassengerQueue object to access the queueArray
    private static String fileName; //Variable to hold the user input for the filename of the plain text file

    public static void main(String[] args) throws Exception {

        String userInput;
        do {
            Scanner option = new Scanner(System.in);

            // Main Menu
            System.out.println("\n    Airport Passenger Boarding System");
            System.out.println("=============================");
            System.out.println("                   Main Menu");
            System.out.println("     **********************************\n");
            System.out.println("A: Add Passenger to passengerQueue");
            System.out.println("V: View passengerQueue");
            System.out.println("D: Delete Passenger from passengerQueue");
            System.out.println("S: Store passengerArray data to Plain Text File");
            System.out.println("L: Load Passenger data from file into passengerArray");
            System.out.println("R: Run Simulation and Produce Report");
            System.out.println("X: Exit\n");

            System.out.print("Enter option: ");
            userInput = option.next().toLowerCase(); // When the user inputs an option it is converted to lower case so that both upper and lower cases can be used for input
            System.out.println("");

            switch (userInput) {
                case "a":
                    addPassenger();
                    break;
                case "v":
                    queue1.display(); //Calling the display() function from the PassengerQueue class
                    break;
                case "d":
                    queue1.remove(); //Calling the remove() function from the PassengerQueue class
                    break;
                case "s":
                    arrayToFile();
                    break;
                case "l":
                    fileToArray();
                    break;
                case "r":
                    simulation();
                    break;
                case "x":
                    System.out.println("***********************************");
                    System.out.println("*  Thank You for using the System *");
                    System.out.println("*        Have a nice day!         *");
                    System.out.println("***********************************");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Invalid Input! Please try again");
                    break;
            }

        } while (!userInput.equals("x")); //Main menu iterates till the user inputs "X" or "x" to terminate the program

    }

    private static void addPassenger() {
        String fName = "";
        String lName = "";
        Scanner passDetails = new Scanner(System.in);

        try {
            // Prompting and storing input given by the user for Passenger Details
            System.out.print("Enter First Name: ");
            fName = passDetails.next();            
            System.out.print("Enter Last Name: ");
            lName = passDetails.next();
        } catch (InputMismatchException e) {
            System.out.println("Invalid Input!"); // To not let the program get interruptd by InputMismatchException for invalid inputs
        }

        Passenger newPassenger = new Passenger(); // A new Passenger object is created for each new passenger
        newPassenger.setFirstName(fName); // Passenger details are passed to the setters of the Passenger class to be stored
        newPassenger.setSurname(lName);

        passengers.add(newPassenger); // Passenger details are stored as an object in the Passenger ArrayList
        queue1.add(newPassenger); // Passenger details are also sent to the queue
    }

    private static void arrayToFile() throws Exception {
        Scanner userFile = new Scanner(System.in);

        // User is prompted to enter a filename for the file
        System.out.print("Input filename(.txt): ");
        fileName = userFile.next();

        PrintWriter writePassenger = new PrintWriter(new File(fileName)); // PrintWriter is used instead of FileWriter because we will be writing objects through toString() method to file

        for (int i = (passengers.size() - 1); i >= 0; i--) {
            writePassenger.print(passengers.get(i) + "\n");
            passengers.remove(i);
            /* After each Passenger object has been written to the file, that passenger is removed from the passenger array
               because otherwise the same names will be duplicated when loading it back from the file*/
        }

        writePassenger.close();
        System.out.println("\nPassenger Array Data successfully written to " + fileName);
    }

    private static void fileToArray() throws Exception {
        String[] passengerName; // Array which holds the split string(first name and last name) from the file 

        Scanner readPassenger = new Scanner(new BufferedReader(new FileReader(fileName)));
        String fileLine;

        while (readPassenger.hasNext()) { //While there exists more lines it will print all lines in the file through the while loop
            fileLine = readPassenger.nextLine();
            passengerName = fileLine.split(" "); // Each line is split by the space to seperate the first name and last name and are stored in the passengerName array
            Passenger passengerFromFile = new Passenger(); // Same as in the addPassenger() method passenger details are added back to the passenger array
            passengerFromFile.setFirstName(passengerName[0]);
            passengerFromFile.setSurname(passengerName[1]);
            passengers.add(passengerFromFile);
        }
        readPassenger.close();
        System.out.println("\nPassenger Data successfully read from " + fileName + " and stored in the Passenger Array");

        for (int i = 0; i < passengers.size(); i++) {
            System.out.println(passengers.get(i) + "\n"); // For loop to check if all passenger details have been properly added back
        }
    }

    private static void simulation() throws Exception {
        System.out.println("     AIRPORT BOARDING SYSTEM SIMULATION");
        System.out.println("*******************************************************");

        // Passenger Array is emptied so that only the sample passengers from the passengers.dat file will be used in the simulation
        for (int i = (passengers.size() - 1); i >= 0; i--) {
            passengers.remove(i);
        }

        // Reinitializing the queue so that the passengers already in the queue will get replaced when the simulation runs
        queue1.setFirst(0);
        queue1.setLast(0);
        queue1.setPassengerCount(0);

        String[] passengerName; // Array which holds the split string(first name and last name) from the file 

        Scanner readPassenger = new Scanner(new BufferedReader(new FileReader("passengers.dat"))); // Loading the file to memory
        String fileLine;

        while (readPassenger.hasNext()) { //While there exists more lines it will print all lines in the file through the while loop
            fileLine = readPassenger.nextLine();
            passengerName = fileLine.split(" "); // Each line is split by the space to seperate the first name and last name and are stored in the passengerName array
            Passenger passengerFromFile = new Passenger(); // Same as in the addPassenger() method passenger details are added back to the passenger array
            passengerFromFile.setFirstName(passengerName[0]);
            passengerFromFile.setSurname(passengerName[1]);
            passengers.add(passengerFromFile);
        }
        readPassenger.close();
        System.out.println(".............................................................................................................................");
        System.out.println("Passenger Data successfully read to Passenger Array");
        System.out.println(".............................................................................................................................\n");

        int delaySeconds; // Variable to hold the delay time for each passenger in the queue
        int arrayMarker = 0; // Variable which holds the index of the next passenger to be taken from the Passenger Array
        int loopCounter = 0; // Variable which keeps track of the number of passengers in the Passenger Array being added to the queue

        while (loopCounter < passengers.size()) {
            int passengersPut = randomGenerator(); // A random number of passengers between 1 to 6 is assigned
            int queueCount = 0; //Variable which holds how many passengers were successfully entered into the queue in each iteration
            System.out.println("....................................................................................................................");
            System.out.println("Passengers taken from Passenger Array: " + passengersPut + "\n");

            for (int i = 0; i < passengersPut; i++) {
                if (!queue1.isFull()) {
                    int die1 = randomGenerator(); // A random number of seconds between 1 to 6 is assigned
                    int die2 = randomGenerator(); // A random number of seconds between 1 to 6 is assigned
                    int die3 = randomGenerator(); // A random number of seconds between 1 to 6 is assigned

                    delaySeconds = sum(die1, die2, die3); // The sum() function is called to add the 3 dice rolls to get a delay value of a maximum of 18 seconds
                    System.out.println("Delay for Passenger " + passengers.get(arrayMarker) + ": " + delaySeconds);

                    passengers.get(arrayMarker).setSecondsInQueue(delaySeconds); // The delay for each passenger is assigned
                    queue1.add(passengers.get(arrayMarker)); // That passenger is added to the queue

                    arrayMarker++;
                    queueCount++;
                    loopCounter++;

                    System.out.println("No. of Passengers in the Queue: " + queue1.passengerCount + "\n");
                } else {
                    System.out.println("Queue is Full! Please wait until there is a slot available");
                    break;
                }
            }

            System.out.println("Passengers added to Queue: " + queueCount);
            queue1.remove(); // The passenger at the front of the queue is removed

        }

        System.out.println("All Passengers successfully moved to Queue\nSIMULATION SUCCESSFUL!\n\n");
        report();
    }

    private static int randomGenerator() {
        int dice = (int) (Math.random() * 6 + 1); // A random number from 1 to 6 including 6 is generated
        return dice;
    }

    private static int sum(int a, int b, int c) {
        int sum = a + b + c; // Calculates the sum of 3 values
        return sum;
    }

    private static void report() throws Exception {
        PrintWriter writeReport = new PrintWriter(new File("report.dat")); // A new file is created to store the report of the simulation and is opened for writing

        System.out.println("    SIMULATION REPORT");
        System.out.println("*********************************\n");
        writeReport.println("    SIMULATION REPORT");
        writeReport.println("*********************************\n");

        System.out.println("Passengers in Passenger Array\n");
        writeReport.println("Passengers in Passenger Array\n");

        // All Passengers in the Passenger Array are displayed and written to file
        for (int i = 0; i < passengers.size(); i++) {
            System.out.println(passengers.get(i));
            writeReport.println(passengers.get(i));
        }
        System.out.println("--------------------------------------------------------------");
        writeReport.println("--------------------------------------------------------------");

        int maxDelay = passengers.get(0).getSecondsInQueue();
        int minDelay = passengers.get(0).getSecondsInQueue();
        int totalDelay = 0;
        double avgDelay = 0;

        for (int i = 0; i < passengers.size(); i++) {
            totalDelay += passengers.get(i).getSecondsInQueue(); // Total delay is calculated by summing up all delays of all passengers in the Passenger Array

            // Simple if condition checks each passenger's delay times to determine the highest and lowest waiting times
            if (passengers.get(i).getSecondsInQueue() > maxDelay) {
                maxDelay = passengers.get(i).getSecondsInQueue();
            } else if (passengers.get(i).getSecondsInQueue() < maxDelay) {
                minDelay = passengers.get(i).getSecondsInQueue();
            }
        }

        avgDelay = totalDelay / passengers.size(); // Average delay is calculated by dividing the total delay with the size of the Passenger Array

        int maxLength = queue1.getMaxSize();
        System.out.println("Maximum Length of Queue Attained: " + maxLength);
        writeReport.println("Maximum Length of Queue Attained: " + maxLength);

        System.out.println("Maximum Waiting Time: " + maxDelay + " seconds");
        writeReport.println("Maximum Waiting Time: " + maxDelay + " seconds");

        System.out.println("Minimum Waiting Time: " + minDelay + " seconds");
        writeReport.println("Minimum Waiting Time: " + minDelay + " seconds");

        System.out.println("Average Waiting Time: " + avgDelay + " seconds");
        writeReport.println("Average Waiting Time: " + avgDelay + " seconds");

        writeReport.close();
    }

}
