package command;

import exception.DukeException;
import exception.InvalidCommand;
import exception.InvalidDateFormat;
import exception.NullDescription;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Command is the over-arching class of all possible commands supported by Duke.
 * Command classes will provide the instruction on how to handle user inputs.
 */
public abstract class Command {

    /**
     * Parses the input given by users and return the correct type of command.
     * Invalid commands will throw the respective DukeExceptions
     *
     * @param input string input by users
     * @return The specific type of command that can be executed
     * @throws InvalidCommand If command cannot be understood
     * @throws NullDescription If AddCommand is intended but without any description
     * @throws InvalidDateFormat If AddCommand is intended but the date format is not followed
     */
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

    /**
     * Execute the given command returned by parse method.
     * Each command class will have its own interaction with Ui, TaskList and Storage
     *
     * @param tasks the TaskList loaded from storage.
     * @param ui Ui interface that prints messages.
     * @param storage accesses the file location in local storage.
     * @throws DukeException If the execution contains unexpected behaviour
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns true if the command is an exit command.
     *
     * @return the boolean true if command is an exit command.
     */
    public abstract boolean isExit();
}
