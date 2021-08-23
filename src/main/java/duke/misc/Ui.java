package duke.misc;

import java.util.Scanner;

public class Ui {
    private Scanner sc;
    static final String LINE = "_________________________________________________________________"
            + "_______________________________________________________________________\n";
    public static final String WELCOME_MSG = "Hi I am Annie!\nHow can I assist you?";
    public static final String GOODBYE_MSG = "Bye. See you soon!";
    static final String LIST_MSG = "Here are the tasks in your list:\n";
    static final String ADD_MSG = "Gotcha. I've added this task:\n";
    static final String NUMTASK_MSG = "Your current task count: ";
    static final String DONE_MSG = "I have marked this task as done:\n";
    static final String DELETE_MSG = "I have deleted this task:\n";
    static final String FIND_MSG = "Here are what i found:\n";
    public Ui() {
        sc = new Scanner(System.in);
    }

    public static void printBlock(String message) {
        System.out.printf("%s%s\n%s", LINE, message, LINE);
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
