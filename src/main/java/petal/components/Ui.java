package petal.components;

import petal.Petal;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ui {

    private final Petal petal;
    private final Scanner scanner;

    public Ui(Petal petal) {
        this.petal = petal;
        scanner = new Scanner(System.in);
    }

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
     * Method to add the indentation to the message
     * @param message Message to be printed
     */
    public void output(Responses message) {
        System.out.println(Responses.LINE + "\n" + message.toString() + "\n" + Responses.LINE);
    }

    /**
     * Method to add the indentation to the message
     * @param message Message to be printed
     */
    public void output(String message) {
        System.out.println(Responses.LINE + "\n" + message + "\n" + Responses.LINE);
    }

}
