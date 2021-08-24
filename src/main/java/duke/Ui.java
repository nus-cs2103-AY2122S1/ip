package duke;

import java.util.Scanner;

/**
 * duke.Ui represents the class that deals with interactions with the user
 */
public class Ui {
    Scanner scanner = new Scanner(System.in);

    /**
     * Prints the duke.Response to the console.
     * @param response The duke.Response to be printed.
     */
    public void printResponse(Response response) {
        System.out.println(response.toString());
    }

    /**
     * Prompts the user for an input.
     * @return The input string
     */
    public String getUserInput() {
        System.out.println();
        return scanner.nextLine();
    }
}
