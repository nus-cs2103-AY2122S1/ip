package duke.ui;
import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo = " ____        _\n"
        + "|  _ \\ _   _| | _____\n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm\n" + logo + "\nHow can I help?");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printOutput(String output) {
        System.out.println(output); 
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showLine() {
        System.out.println("-------------------------------------------------------------------------------");
    }

    public void showLoadingError() {
        System.out.println("☹ OOPS!!! File couldn't be loaded");
    }

}
