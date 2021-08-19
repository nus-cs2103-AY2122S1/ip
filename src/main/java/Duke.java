import java.util.Scanner;
import java.util.function.DoubleToIntFunction;

/**
 * A class that encapsulates the duke chat bot, which has ability to greet, echo and exit.
 */
public class Duke {
    private String[] list = new String[100];
    int counter = 0;

    /**
     * Constructor for the Duke class
     * Does not take in any parameters, prints out a statement to greet user.
     */

    public Duke() {
        System.out.println("\tHello! I'm Duke\n\tWhat can I do for you?");
    }

    /**
     * method to add a text to the list in the chat bot,
     * adds the text to the next available index of the list,
     * then prints a statement saying the text has been added.
     * @param input
     */
    public void add(String input) {
        list[counter] = input;
        counter++;
        System.out.println("\tadded: " + input);
    }

    /**
     * method to iteratively print out the texts stored in the list
     * in order of which they are added
     */
    
    public void iterate() {
        for(int i = 0; i < counter; i++) {
            System.out.println("\t" + (i + 1) + ". " + list[i]);
        }
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
            if (input.equals("list")) {
                duke.iterate();
            } else {
                duke.add(input);
            }
                input = sc.nextLine();
        }
        System.out.println("\tBye. Hope to see you again soon!");
        sc.close();
    }
}
