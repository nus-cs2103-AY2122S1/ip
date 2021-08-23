package duke.command;

import duke.DukeException;
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
        EXIT("^bye *?");

        public String commandRegex;

        Type(String str) {
            this.commandRegex = str;
        }
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException;

    public boolean isExit() {
        return this instanceof ExitCommand;
    }

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
            case EXIT:
                return new ExitCommand();
            default:
                throw new DukeException();
        }
    }
}
