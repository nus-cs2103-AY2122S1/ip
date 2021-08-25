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
        while (!this.isStopped) {
            try {
                listen();
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
     * Listens for user input and parses user's input.
     *
     * @throws DukeException If input is invalid.
     */
    public void listen() throws DukeException {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Parser parser = new Parser(input);
        Command command = Command.identifyCommand(parser.getCommandWord());
        command.run(this, parser);
    }
}
