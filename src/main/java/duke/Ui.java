package duke;
import java.util.Scanner;

public class Ui {

    public static void displayLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static void greet() {
        String greeting = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
        System.out.println(greeting);
    }

    public void showLoadingError() {
        String errorMessage = "Encountered an error loading task list from file.\n";
        System.out.println(errorMessage);
    }

    public void showWelcome() {
        displayLogo();
        greet();
    }

    public String readCommand() {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        return str;
    }

    public void showLine() {
        System.out.println("____________________________________________________________\n");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showResponse(String message) {
        System.out.println(message);
    }
}
