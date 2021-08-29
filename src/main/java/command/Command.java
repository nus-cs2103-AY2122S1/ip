package command;

import duke.Duke;
import duke.DukeException;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    protected String args;

    protected Command(String args){
        this.args = args;
    }

    /**
     * Returns Command created by user input.
     *
     * @param input User input.
     * @return Command created.
     * @throws DukeException if command is invalid.
     */
    public static Command createCommand(String input) throws DukeException {
        String[] cmd_args = Parser.parseUserInput(input);
        Command cmd;
        switch (cmd_args[0]){
        case "bye":
            cmd = new ByeCommand();
            break;
        case "list":
            cmd = new ListCommand();
            break;
        case "done":
            cmd = new DoneCommand(cmd_args[1]);
            break;
        case "delete":
            cmd = new DeleteCommand(cmd_args[1]);
            break;
        case "find":
            cmd = new FindCommand(cmd_args[1]);
            break;
        case "todo":
            cmd = new TodoCommand(cmd_args[1]);
            break;
        case "event":
            cmd = new EventCommand(cmd_args[1]);
            break;
        case "deadline":
            cmd = new DeadlineCommand(cmd_args[1]);
            break;
        default:
            throw new DukeException("command not found");
        }
        return cmd;
    }

    public abstract void execute(TaskList tasklist, Ui ui, Storage store, Duke bot);
}
