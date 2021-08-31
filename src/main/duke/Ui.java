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
    public void showWelcome() {
        System.out.println("Hello! I'm Duke! What can I do for you?");
    }

    /**
     * Display error message.
     * @param error Input error message.
     */
    public void showError(String error) {
        System.out.println(String.format("Error! %s", error));
    }

    /**
     * Display message when file is absent during loading.
     */
    public void showLoadingError() {
        System.out.println("Oops! Cannot load file! We will start afresh :)");
    }

    /**
     * Display divider line between user command and Duke chatbot response.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Print input message.
     * @param input Input message.
     */
    public void print(String input) {
        System.out.println(String.format("%s", input));
    }

    /**
     * Read user command.
     * @return User command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
