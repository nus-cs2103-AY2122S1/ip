import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Duke {
    /** Simple string array to store inputs **/
    private Task[] list;
    /** Simple index to keep track of the current element in the list**/
    private int index;
    /** Enum for handling user command**/
    public enum Commands {
        BYE, LIST, DONE
    }

    /** Basic constructor to initialise the list **/
    public Duke() {
        this.list = new Task[100];
        this.index = 0;
    }

    /**
     * Private method to append items to the back of the list.
     * @param text The text to be appended.
     */
    private void append(String text) {
        this.list[index] = new Task(text);
        this.index ++;
    }

    /**
     * Simple method that iterates through the task list and prints out all the entries
     */
    public void list() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.index; i++) {
            // i + 1 is used to start indexing from 1
            output.append(i + 1).append(". ");
            output.append(this.list[i].toString());
            // ensure the newline is not added on the last element
            if (i != this.index - 1) {
                output.append("\n");
            }
        }
        this.printMsg(output.toString());
    }


    /**
     * Method to be called when the first argument to the input is "done".
     * Parses the second argument as an integer and checks if it is a valid index.
     * If valid, marks the task that is currently stored in memory to be complete.
     *
     * @param text Command line argument string
     */
    private void done(String text) {
        // check for the existence of the second argument and that it can be parsed to an int for lookup
        String[] splitted = text.split(" ");
        // TODO: Ensure that index 1 is valid. Throw an error if no secondary argument is provided on "done"
        if (splitted.length < 2) {
            printMsg("Command is missing an argument 'index'.");
            return;
        }

        String dirtyInt = splitted[1];


        // ensure that it is defined
        if (dirtyInt != null) {
            // try to parse it into an int
            try {
                int lookup = Integer.parseInt(dirtyInt);
                if (lookup < 1) {
                    this.printMsg(String.format("The specified task number %d does not exist. Enter a number that is at least 1.", lookup));
                }
                else if (lookup < this.index + 1) {
                    Task task = this.list[lookup - 1];
                    task.markAsDone();
                    this.printMsg(String.format("Nice! I've marked this task as done:\n  %s", task));
                } else {
                    this.printMsg(String.format("The task number %s is invalid, please enter a valid " +
                            "number.", lookup));
                }
            } catch(NumberFormatException e) {
                printMsg(String.format("The specified task number %s, is not a valid integer, please " +
                        "enter a valid numeric task number.", dirtyInt));
            }
        } else {
            System.out.println(Arrays.toString(splitted));
        }


    }


    /**
     * Simple formatting tool to be used when printing commands
     * @param text The text to be printed.
     */
    private void printMsg(String text) {
        String bar = "===============================================";
        System.out.printf("%s\n%s\n%s\n", bar, text, bar);
    }


    /**
     * Method that listens and scans for user input.
     * Program will exit when the command "bye" is given to the scanner.
     */
    public void listen() {
        Scanner scanner = new Scanner(System.in);
        this.printMsg("Hello! I'm Duke\nWhat can I do for you?");
        boolean terminate = false;
        while (!terminate) {
            String text = scanner.nextLine().toLowerCase();
            // get the first word of the text
            String command = text.split(" ")[0];
            switch (command.toLowerCase()) {
                // break out of the while loop
                case ("bye") -> {
                    this.printMsg("Bye. Hope to see you again soon!");
                    scanner.close();
                    terminate = true;
                }
                // list out the items in the list if "list" is called
                case ("list") -> this.list();

                case("done") -> {
                    this.done(text);
                }

                default -> {
                    this.append(text);
                    this.printMsg("added: " + text);
                }
            }
        }
    }

}
