package duke;

import java.util.Scanner;

public class Ui {
    
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke! What can I do for you?");
    }

<<<<<<< Updated upstream
    public void showError(String error) {
        System.out.println(String.format("Error! %s", error));
    }

    public void showLoadingError() {
        System.out.println("Oops! Cannot load file! We will start afresh :)");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public void print(String input) {
        System.out.println(String.format("%s", input));
=======
    /**
     * Display message when Duke chatbot ends.
     */
    public void showGoodbye() {
        System.out.println("Goodbye! Have a nice day!");
    }

    /**
     * Display divider line between user command and Duke chatbot response.
     */
    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    /**
     * Display input message.
     */
    public void showMessage(String message) {
        System.out.println(String.format("%s", message));
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
        showError("Oops! Cannot load file! We will start afresh :)");
>>>>>>> Stashed changes
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
