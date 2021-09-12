package duke.util;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Stream;

import duke.exception.DukeException;

/**
 * The UI class encapsulates information
 * and methods pertaining to the user-interface in Duke.
 *
 * @author Frederick Pek
 * @version CS2103 AY21/22 Sem 1 iP
 */
public class UI {
    private final List<String> outputLines;

    /**
     * Creates and initalizes a new UI instance.
     */
    public UI() {
        this.outputLines = new ArrayList<>();
    }

    /**
     * Returns the start-up message of Duke.
     * 
     * @return Return the String start-up message of Duke.
     */
    public String getStartUpMessage() {
        add("Hello! I'm Duke :D");
        add("I can help you keep track of tasks!");
        add("\nType 'help' to see a list of command I can perform!");
        return getOutput();
    }

    /**
     * Returns the help message of Duke. This includes the list
     * of useful commands Duke offers.
     * 
     * @return Return the String help message of Duke.
     */    
    public String getHelpMessage() {
        return "These are some of the commands I support:\n"
                + "\nTask creation commands:\n"
                + "    todo <description>\n"
                + "    deadline <description> /by <deadline>\n"
                + "    event <description> /at <period>\n"
                + "\nOther commands include:\n"
                + "    list\n"
                + "    find <keyword>\n"
                + "    done <index>\n"
                + "    delete <index>\n"
                + "    sort\n"
                + "    bye\n"
                + "    help\n"
                + "\nFor a more comprehensive guild,\nplease visit the user guide!";
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
        String output = outputLines.stream()
                .map(line -> line + "\n")
                .reduce("", (x, y) -> x + y);
        clear();
        return output;
    }
}
