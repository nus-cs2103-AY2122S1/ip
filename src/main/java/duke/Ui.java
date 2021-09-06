package duke;

import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Display message when Duke chatbot begins.
     */
    public String showWelcome() {
        return("Hello! I'm Duke! What can I do for you?");
    }

    /**
     * Display message when Duke chatbot ends.
     */
    public String showGoodbye() {
        return("Goodbye! Have a nice day!");
    }

    /**
     * Display divider line between user command and Duke chatbot response.
     */
    public String showLine() {
        return("____________________________________________________________");
    }

    /**
     * Display input message.
     */
    public String showMessage(String message) {
        return(String.format("%s", message));
    }

    /**
     * Display error message.
     * @param error Input error message.
     */
    public String showError(String error) {
        return(String.format("Error! %s", error));
    }

    /**
     * Display message when file is absent during loading.
     */
    public String showLoadingError() {
        return(showError("Oops! Cannot load file! We will start afresh :)"));
    }

    /**
     * Read user command.
     * @return User command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
