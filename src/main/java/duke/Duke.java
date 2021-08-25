package duke;

import duke.command.Command;

import java.util.Scanner;

/**
 * This class represents an instance of the Duke chatbot.
 */
public class Duke {
    private final TaskList list = new TaskList();
    private boolean isStopped = false;

    /**
     * Starts the Duke chatbot.
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

    /**
     * Stops the Duke chatbot.
     */
    public void stop() {
        Ui.goodbyeMessage();
        this.isStopped = true;
    }

    /**
     * Gets the task list stored in the chatbot.
     *
     * @return Task list of the current instance.
     */
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
