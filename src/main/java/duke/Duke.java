package duke;

import duke.command.Command;

/**
 * This class represents an instance of the Duke chatbot.
 */
public class Duke {
    private TaskList list;

    /**
     * Constructor for a Duke instance.
     * Initialises Duke's task list.
     */
    public Duke() {
        try {
            list = new TaskList();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
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
    public String listen(String input) throws DukeException {
        Parser parser = new Parser(input);
        Command command = Command.identifyCommand(parser.getCommandWord());
        return command.run(this, parser);
    }
}
