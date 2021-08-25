package duke;

import java.util.Scanner;

/**
 * A collection of UI methods.
 */
public class Ui {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Print a message that is enclosed by 2 horizontal lines.
     *
     * @param message The message to be printed between 2 horizontal lines.
     */
    public void printMessage(String message) {
        String horizontalLine = "____________________________________________________________";
        System.out.println(horizontalLine + "\n" + message + "\n" + horizontalLine);
    }

    /**
     * Greeting message of Duke.
     */
    public void greet() {
        String logo = " ____        _\n"
                + "|  _ \\ _   _| | _____\n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        printMessage(logo + "\nWelcome! I'm Duke\nWhat can I do for you?");
    }

    /**
     * Get the next line of input.
     *
     * @return The next line of input.
     */
    public String getNextLine() {
        try {
            return this.scanner.nextLine();
        } catch (Exception e) {
            printMessage(e.toString());
            return null;
        }
    }

    /**
     * Exit message of ui.
     */
    public void bye() {
        this.scanner.close();
        this.printMessage("Bye. See you next time!");
    }

}
