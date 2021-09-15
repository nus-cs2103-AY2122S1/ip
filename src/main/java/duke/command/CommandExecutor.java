package duke.command;

import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Represents a CommandExecutor class that executes user input command.
 *
 * @author Chen Hsiao Ting
 * @version CS2103T AY21/22 Semester 1
 */
public class CommandExecutor {
    protected final TaskList tasks;
    protected final Storage storage;
    protected final Ui ui;

    /**
     * A constructor for CommandExecutor.
     *
     * @param tasks   A list of current Tasks.
     * @param storage Storage file.
     * @param ui      Ui to log the execution of the command.
     */
    public CommandExecutor(TaskList tasks, Storage storage, Ui ui) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Executes user command.
     *
     * @param input User input.
     * @return String representation of the executed command.
     * @throws DukeException If user input is invalid.
     */
    public String execute(String input) throws DukeException {
        Parser parser = new Parser(input);
        String command = parser.getCommandWord();

        if (command.equals("bye")) {
            return new ByeCommand(tasks, parser, storage, ui).bye();
        } else if (command.equals("list")) {
            return new ListCommand(tasks, parser, storage, ui).list();
        } else if (command.equals("find")) {
            return new FindCommand(tasks, parser, storage, ui).find();
        } else if (command.equals("done")) {
            return new DoneCommand(tasks, parser, storage, ui).done();
        } else if (command.equals("delete")) {
            return new DeleteCommand(tasks, parser, storage, ui).delete();
        } else if (command.equals("todo")) {
            return new AddCommand(tasks, parser, storage, ui).addTodo();
        } else if (command.equals("deadline")) {
            return new AddCommand(tasks, parser, storage, ui).addDeadline();
        } else if (command.equals("event")) {
            return new AddCommand(tasks, parser, storage, ui).addEvent();
        } else if (command.equals("help")) {
            return new HelpCommand(tasks, parser, storage, ui).help();
        } else {
            throw new DukeException("Command is not valid!");
        }
    }
}
