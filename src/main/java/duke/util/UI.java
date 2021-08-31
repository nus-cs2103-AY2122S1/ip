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
    private final List<String> outputLines;

    /**
     * Creates and initalizes a new UI instance.
     */
    public UI() {
        this.tab = " ".repeat(4);
        this.outputLines = new ArrayList<>();
    }

    /**
     * Returns the start-up message of Duke.
     * 
     * @return Return the String start-up message of Duke.
     */
    public String getStartUpMessage() {
        add("Hello! I'm Duke");
        add("What can I do for you?");
        return getOutput();
    }

    /**
     * Returns the error message of the specified exception.
     *
     * @param ex The exception whose error messages will be printed.
     * @return Returns the String error message of the specified duke exception.
     */
    public String getErrorMessage(DukeException ex) {
        add(ex.getMessage());
        for (String line : ex.getHelpMessages()) {
            add(line);
        }
        return getOutput();
    }

    /**
     * Appends the line of output to the list of output lines.
     *
     * @param line The string to be appended to the output lines.
     */
    public void add(String line) {
        this.outputLines.add(line);
    }

    /**
     * Clears the list of output lines.
     */
    public void clear() {
        this.outputLines.clear();
    }

    /**
     * Returns the output as a single string,
     * then clears the output list.
     */
    public String getOutput() {
        String output = "";
        for (String line : outputLines) {
            output += tab + " " + line + "\n";
        }
        clear();
        return output;
    }
}
