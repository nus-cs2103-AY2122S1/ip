package duke.command;

import duke.exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public abstract class Command {

    public enum Type {
        ADD("(todo|deadline|event)"),
        DELETE("delete"),
        LIST("^list *?"),
        MARK_DONE("done"),
        FIND("find"),
        UPDATE("update"),
        EXIT("^bye *?");

        public String commandRegex;

        Type(String str) {
            this.commandRegex = str;
        }
    }

    /**
     * Execute the command.
     *
     * @param taskList The current TaskList.
     * @param ui The Ui.
     * @param storage The storage.
     * @throws DukeException Teh exception thrown when the command is unable to execute.
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return this instanceof ExitCommand;
    }

    /**
     * Creates a command of a given type from a given input.
     *
     * @param type The type of command.
     * @param input The given input.
     * @return A command.
     * @throws DukeException The exception thrown when a command cannot be created.
     */
    public static Command createCommand(Command.Type type, String input) throws DukeException {
        switch (type) {
            case ADD:
                return new AddCommand(input);
            case DELETE:
                return new DeleteCommand(input);
            case LIST:
                return new ListCommand();
            case MARK_DONE:
                return new MarkDoneCommand(input);
            case FIND:
                return new FindCommand(input);
            case UPDATE:
                return new UpdateCommand(input);
            case EXIT:
                return new ExitCommand();
            default:
                throw new DukeException();
        }
    }
}
