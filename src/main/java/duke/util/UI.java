package duke.util;

import java.util.List;
import java.util.ArrayList;

import duke.exception.DukeException;

/**
 * The UI class encapsulates information
 * and methods pertaining to the user-interface in Duke.
 *
 * @author Frederick Pek
 * @version CS2103 AY21/22 Sem 1 iP
 */
public class UI {
    private final String tab;
    private final String horizontalLine;
    private final List<String> list;

    /**
     * Creates and initalizes a new UI instance.
     */
    public UI() {
        this.tab = " ".repeat(4);
        this.horizontalLine = "_".repeat(60);
        this.list = new ArrayList<>();
    }

    /**
     * Prints a start-up message to standard output.
     */
    public String printStartUpMessage() {
        add("Hello! I'm Duke");
        add("What can I do for you?");
        return print();
    }

    /**
     * Prints the error message of the specified exception.
     *
     * @param ex The exception whose error messages will be printed.
     */
    public String printErrorMessage(DukeException ex) {
        add(ex.getMessage());
        for (String line : ex.getHelpMessages()) {
            add(line);
        }
        return print();
    }

    /**
     * Appends the line of output to the list of lines to be printed.
     *
     * @param line The string to be appended to the list.
     */
    public void add(String line) {
        this.list.add(line);
    }

    /**
     * Clears the list containing lines to be printed.
     */
    public void clear() {
        this.list.clear();
    }

    /**
     * Prints all lines in the list of lines to be printed,
     * then clears the list.
     */
    public String print() {
        String output = "";
        for (String line : list) {
            output += tab + " " + line + "\n";
        }
        clear();
        return output;
    }
}
