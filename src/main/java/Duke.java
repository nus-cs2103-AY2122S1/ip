import java.util.Locale;
import java.util.Scanner;

public class Duke {
    /** Simple string array to store inputs **/
    String[] list;
    /** Simple index to keep track of the current element in the list**/
    int index;

    /** Basic constructor to initialise the list **/
    public Duke() {
        this.list = new String[100];
        this.index = 0;
    }

    /**
     * Private method to append items to the back of the list.
     * @param text The text to be appended.
     */
    private void append(String text) {
        this.list[index] = text;
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
            output.append(this.list[i]);
            // ensure the newline is not added on the last element
            if (i != this.index - 1) {
                output.append("\n");
            }
        }
        this.printMsg(output.toString());
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
            switch (text.toLowerCase()) {
                // break out of the while loop
                case ("bye") -> {
                    this.printMsg("Bye. Hope to see you again soon!");
                    scanner.close();
                    terminate = true;
                }
                // list out the items in the list if "list" is called
                case ("list") -> this.list();

                default -> {
                    this.append(text);
                    this.printMsg("added: " + text);
                }
            }
        }
    }

}
