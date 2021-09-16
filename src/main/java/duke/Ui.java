package duke;

import java.util.Scanner;

public class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Display message when Duke chatbot ends.
     */
    public String showGoodbye() {
        return "Goodbye! Have a nice day!";
    }

    /**
     * Display error message.
     * @param error Input error message.
     */
    public String showError(String error) {
        return String.format("Error! %s", error);
    }

    /**
     * Display message when file is absent during loading.
     */
    public String showLoadingError() {
        return showError("Oops! Cannot load file! We will start afresh :)");
    }

}
