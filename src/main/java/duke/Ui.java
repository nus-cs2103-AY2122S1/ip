package duke;

import java.util.Scanner;

public class Ui {

    /**
     * Displays start message.
     */
    public void startMessage() {
        String greeting = "Why hello there! It's Duke here!\n" + "How can I help you today master?";
        System.out.println(greeting);
    }

    /**
     * Initialises the user input.
     *
     * @return User input.
     */
    public String startInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        return input;
    }

    public String byeMessage() {
        return "See ya again later!";
    }

    /**
     * Returns an error message for the user.
     */
    public String invalidInput() {
        String response = "";
        try {
            throw new InputError("Invalid Input");
        } catch (InputError e) {
            response = "Here is the error boss. " + e.getMessage() + "\n" + "I'm not too sure what you meant.\n"
                    + "Try again with these keywords.\n" + "todo deadline event";
        }
        return response;
    }

    public String errorMessage(InputError e) {
        return "Here is the error boss. " + e.getMessage();
    }
}
