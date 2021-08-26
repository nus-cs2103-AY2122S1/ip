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
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
