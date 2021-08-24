import java.util.Scanner;

public class Ui {
    private final String OPENING_MESSAGE = "Hello! I'm Duke\n"
            + "What can I do for you?";
    private final String CLOSING_MESSAGE = "Bye. Hope to see you again soon!";
    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
        System.out.println(OPENING_MESSAGE);
    }

    public String readCommand() {
        return sc.nextLine().trim();
    }

    public void showError(DukeException de) {
        System.out.println(de.getMessage());
    }

    public void exit() {
        sc.close();
        System.out.println(CLOSING_MESSAGE);
    }
}
