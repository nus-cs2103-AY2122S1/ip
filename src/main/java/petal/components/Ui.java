package petal.components;

import petal.Petal;
import java.util.Scanner;

/**
 * The Ui is responsible for handling the output, and interactions
 * with the user
 */
public class Ui {

    private final Petal petal;
    private final Scanner scanner;

    /**
     * The constructor for the Ui class
     *
     * @param petal The instance of the Petal bot
     */
    public Ui(Petal petal) {
        this.petal = petal;
        scanner = new Scanner(System.in);
    }

    /**
     * Method that reads the input from the user
     *
     * @return String that represents the user's input
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Method for Petal to say goodbye. In the case saveTasks() throws an error,
     * Petal does not save any of the tasks.
     */
    public void goodBye() {
        output(Responses.GOODBYE);
        scanner.close();
        petal.stop();
    }

    /**
     * Method that outputs the given parameter, and is padded by the line so it can
     * be displayed to the user in a compartmentalized fashion
     *
     * @param message Response that is converted to string that then displayed
     */
    public void output(Responses message) {
        System.out.println(Responses.LINE + "\n" + message.toString() + "\n" + Responses.LINE);
    }

    /**
     * Method that outputs the given parameter, and is padded by the line, so it can
     * be displayed to the user in a compartmentalized fashion
     *
     * @param message String to be printed
     */
    public void output(String message) {
        System.out.println(Responses.LINE + "\n" + message + "\n" + Responses.LINE);
    }

}
