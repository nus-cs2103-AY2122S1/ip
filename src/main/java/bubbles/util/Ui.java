package bubbles.util;

import java.util.Scanner;

/**
 * A class that deals with interactions with the user,
 * mainly accepting user input and echoing back to the user
 * through System output.
 */
public class Ui {
    private final Scanner sc;

    /* The Storage Object where the final list of tasks
     * would be updated to, after the Bubbles bot program
     * terminates.
     */
    private final Storage storage;

    /** Constructor for Ui. */
    public Ui(Storage storage) {
        this.storage = storage;

        sc = new Scanner(System.in);
    }

    /**
     * Echos the user input through printing out the input
     * String onto the System output line.
     */
    public void echo() {
        while (sc.hasNext()) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println(Message.EXIT);
                break;
            } else {
                System.out.println(input);
            }
        }

        sc.close();
    }

    /**
     * Echos the (accepted forms of) user input after formatting,
     * where the accepted commands are to add ToDo/Deadline/Event tasks,
     * marking tasks as done, deleting tasks, listing out all of the
     * tasks and saying good-bye.
     */
    public String readCommand() {
        return sc.nextLine().trim();
    }

    /**
     * Closes the scanner, write the tasks into the local storage
     * and prints the Exit message.
     *
     * @return String representing the Exit message.
     */
    public String exit() {
        sc.close();

        storage.writeTasks();
        return Message.EXIT.toString();
    }
}
