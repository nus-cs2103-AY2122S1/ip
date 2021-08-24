package duke;

import duke.command.Command;
import java.util.Scanner;

/**
 * This class represents a Duke instance.
 */
public class Duke {
    private final TaskList list = new TaskList();
    private boolean isStopped = false;

    /**
     * Prints welcome message, then accepts user input until exit command is entered.
     */
    public void start() {
        Ui.welcomeMessage();
        Scanner sc = new Scanner(System.in);
        while (!this.isStopped) {
            String input = sc.nextLine();
            try {
                listen(input);
            } catch (DukeException e) {
                Ui.formatAndPrint(e.getMessage());
            }
        }
    }

    public void stop() {
        Ui.goodbyeMessage();
        this.isStopped = true;
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
}
