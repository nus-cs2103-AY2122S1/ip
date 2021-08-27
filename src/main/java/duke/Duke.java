package duke;

import duke.parser.Parser;
import duke.task.TaskList;
import duke.ui.UserInterface;

import java.util.Locale;
import java.util.Scanner;

public class Duke {
    private static final TaskList TASK = new TaskList();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final Parser PARSER = new Parser();
    private static final UserInterface USER_INTERFACE = new UserInterface();

    /**
     * The main method that drives the operation and execution of the
     * Duke program.
     *
     * @param args user input during the execution of program
     */
    public static void main(String[] args) {

        USER_INTERFACE.greet();
        TASK.loadArrayList(); // Loads the array list based on our file on hard disk
        boolean breakWhile;

        while (SCANNER.hasNext()) {
            String firstWord = SCANNER.next().toLowerCase(Locale.ROOT);
            breakWhile = PARSER.firstCommandParser(firstWord, SCANNER);
            if (breakWhile) {
                break;
            }
        }
    }
}
