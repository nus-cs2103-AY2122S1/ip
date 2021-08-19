import java.util.Scanner;

/**
 * A class that encapsulates the duke chat bot, which has ability to greet, echo and exit.
 */
public class Duke {
    /**
     * Constructor for the Duke class
     * Does not take in any parameters, prints out a statement to greet user.
     */
    public Duke() {
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println("\t" + input);
            input = sc.nextLine();
        }
        System.out.println("\tBye. Hope to see you again soon!");
        sc.close();
    }
}
