import java.util.Scanner;

public class Ui {

    private final static String LINE = "____________________________________________________________";
    private final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private Scanner sc = new Scanner(System.in);

    public String getUserInput() {
        String userInput = sc.nextLine();
        return userInput;
    }

    public void printMessage(String message) {
        System.out.println(String.format("%s\n%s\n%s", LINE, message, LINE));
    }

    public void sayGreeting() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public void sayBye() {
       printMessage(EXIT_MESSAGE);
    }

    public void printError(DukeException e) {
        printMessage(e.toString());
    }

}
