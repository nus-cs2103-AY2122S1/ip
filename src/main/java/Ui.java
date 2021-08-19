import java.util.Scanner;

public class Ui {

    static String logo = "      ____        _        \n"
            + "     |  _ \\ _   _| | _____ \n"
            + "     | | | | | | | |/ / _ \\\n"
            + "     | |_| | |_| |   <  __/\n"
            + "     |____/ \\__,_|_|\\_\\___|\n";
    static String divider = "    ____________________________________________________________";
    static String space = "     ";
    static String INVALID_INPUT = space + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void greet() {
        System.out.println(divider);
        System.out.println(logo);
        System.out.println(space + "Hello! I'm Duke\n" + space + "What can I do for you?");
        System.out.println(divider + "\n");
    }

    public void dismiss() {
        displayText(space + "Bye. Hope to see you again soon!");
    }

    public String readLine() {
        return scanner.nextLine();
    }

    public void showError(String message) {
        displayText(message);
    }

    public void displayText(String text) {
        System.out.println(divider);
        System.out.println(text);
        System.out.println(divider + "\n");
    }

}
