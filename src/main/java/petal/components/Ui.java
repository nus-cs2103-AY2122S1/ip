package petal.components;

import java.util.Scanner;

import petal.Petal;

/**
 * The Ui is responsible for handling the output, and interactions
 * with the user
 */
public class Ui {

    private final Petal petal;
    private final Scanner scanner;

    /**
     * Constructor for the Ui class
     *
     * @param petal The instance of the Petal bot
     */
    public Ui(Petal petal) {
        this.petal = petal;
        scanner = new Scanner(System.in);
    }

    /**
     * Reads input from the user
     *
     * @return String that represents the user's input
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Terminates the Petal instance
     */
    public void sayGoodbye() {
        output(Responses.GOODBYE);
        scanner.close();
        petal.stop();
    }

    /**
     * Outputs given message with lines above and below the message
     *
     * @param message Response that is converted to string that then displayed
     */
    public void output(Responses message) {
        System.out.println(Responses.LINE + "\n" + message.toString() + "\n" + Responses.LINE);
    }

    /**
     * Outputs given message with lines above and below the message
     *
     * @param message String to be printed
     */
    public void output(String message) {
        System.out.println(Responses.LINE + "\n" + message + "\n" + Responses.LINE);
    }

}
