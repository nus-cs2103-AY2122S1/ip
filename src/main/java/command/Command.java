package command;

import exception.DukeException;
import exception.InvalidCommand;
import exception.InvalidDateFormat;
import exception.NullDescription;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public abstract class Command {
    public static Command parse(String input) throws InvalidCommand, NullDescription, InvalidDateFormat {
        String[] output = input.split(" ");
        if (output.length == 0 || output[0].isEmpty() || output[0].equals(" ")) {
            throw new InvalidCommand();
        }

        String command = output[0];
        Command commandType = null;

        switch (command) {
            case "bye":
                commandType = new ExitCommand();
                break;
            case "done":
                commandType = new DoneCommand(Integer.parseInt(output[1]) - 1);
                break;
            case "delete":
                commandType = new DeleteCommand(Integer.parseInt(output[1]) - 1);
                break;
            case "list":
                commandType = new ListCommand();
                break;
            case "find":
                commandType = new FindCommand(output[1]);
                break;
            case "todo":

            case "deadline":

            case "event":
                commandType = new AddCommand(input);
                break;
            default:
                throw new InvalidCommand();
        }
        return commandType;
    }
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
