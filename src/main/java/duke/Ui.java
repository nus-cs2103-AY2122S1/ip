package duke;

import java.util.Scanner;

public class Ui {

    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private Scanner sc = new Scanner(System.in);

    public String getUserInput() {
        String userInput = sc.nextLine();
        return userInput;
    }

    public String printMessage(String message) {
       return String.format("Duke says:\n%s", message);
    }

    public String sayBye() {
        return printMessage(EXIT_MESSAGE);
    }

    public String printError(DukeException e) {
        return printMessage(e.toString());
    }

}
