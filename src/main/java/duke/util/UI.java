package duke.util;

import java.util.*;
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
    private final Scanner sc;

    /**
     * Creates and initalizes a new UI instance.
     */
    public UI() {
        this.tab = " ".repeat(4);
        this.horizontalLine = "_".repeat(60);
        this.list = new ArrayList<>();
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints a start-up message to standard output.
     */
    public void printStartUpMessage() {
        add("Hello! I'm Duke");
        add("What can I do for you?");
        print();
    }

    /**
     * Prints the error message of the specified exception.
     *
     * @param ex The exception whose error messages will be printed.
     */
    public void printErrorMessage(DukeException ex) {
        add(ex.getMessage());
        for (String line : ex.getHelpMessages()) {
            add(line);
        }
        print();
    }

    /**
     * Reads the next line from standard input.
     *
     * @return Returns the line read.
     */
    public String readLine() {
        return sc.nextLine();
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
    public void print() {
        System.out.println(tab + horizontalLine);
        for (String line : list) {
            System.out.println(tab + " " + line);
        }
        System.out.println(tab + horizontalLine);
        System.out.println();
        clear();
    }
}
