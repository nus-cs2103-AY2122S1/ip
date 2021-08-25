import java.util.Scanner;

/**
 * This class is responsible for the printing to console
 * and accepting responses from the user.
 *
 * Responses are passed on to a parser which will parse the user input
 */
public class Ui {

    private static final String SEP_LINE = "____________________________________________________________";

    private DukeParser parser;

    Ui() {
        this.parser = new DukeParser();
    }

    public void greet() {
        System.out.println("Hello! I'm Duke. \n"
                + "What can I do for you? \n"
                + SEP_LINE);
    }

    public void printSepLine() {
        System.out.println(SEP_LINE);
    }

    public void printAddTask(Task t) {
        System.out.println("The following task has been added! \n" + t);
    }

    public void printTaskDone(Task t, int index) {
        System.out.println("The task has been marked as done! \n" + (index + 1) + ". " + t);
    }

    public Command parseInput(String s) {
        return this.parser.parse(s);
    }
}
