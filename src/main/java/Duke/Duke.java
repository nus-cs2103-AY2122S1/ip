package Duke;

import Duke.Command.Command;
import Duke.Task.Deadline;
import Duke.Task.Event;
import Duke.Task.Task;
import Duke.Task.Todo;

import java.time.LocalDate;
import java.util.Scanner;

/**
 * This class represents a Duke instance.
 */
public class Duke {
    private final TaskList list = new TaskList();
    private static final String WELCOME_MESSAGE = "Hello, I'm Duke\nWhat can I do for you?";
    private static final String GOODBYE_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String LINE = "____________________________________________________________\n";
    private boolean isStopped = false;

    /**
     * Prints welcome message, then accepts user input until exit command is entered.
     */
    public void start() {
        formatAndPrint(WELCOME_MESSAGE);
        Scanner sc = new Scanner(System.in);
        while (!this.isStopped) {
            String input = sc.nextLine();
            try {
                listen(input);
            } catch (DukeException e) {
                formatAndPrint(e.getMessage());
            }
        }
    }

    public void stop() {
        this.isStopped = true;
        formatAndPrint(GOODBYE_MESSAGE);
    }

    public TaskList getList() {
        return this.list;
    }

    /**
     * Accepts user input and runs the appropriate function.
     *
     * @param input String containing user input.
     * @return Boolean that controls whether to continue accepting user input.
     */
    public void listen(String input) throws DukeException {
        Parser parser = new Parser(input);
        Command command = Command.identifyCommand(parser.getCommandWord());
        command.run(this, parser);
    }

    /**
     * Helper function to format output between 2 lines.
     *
     * @param s String to be outputted.
     */
    public static void formatAndPrint(String s) {
        System.out.printf("%s%s\n%s", LINE, s, LINE);
    }
}
