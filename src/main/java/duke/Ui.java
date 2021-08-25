package duke;

import java.util.Scanner;

/**
 * To deal with interactions with the user.
 */
public class Ui {

    public Ui() {
    }

    /**
     * Prints the welcome message.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Reads the user input.
     * @return returns the user input 
     */
    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        return command;
    }

    /**
     * Prints the closing message.
     */
    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Prints the Loading Error Message.
     */
    public void showLoadingError(){
        System.out.println("There was an error reading the saved file :(");
    }


}
