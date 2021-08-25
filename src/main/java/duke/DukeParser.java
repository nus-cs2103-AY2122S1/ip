package duke;

import duke.exception.DukeFileSystemException;

public class DukeParser {
    public DukeParser() {

    }

    /**
     * Provides a task based on data read from line.
     *
     * @param line line that data is read from.
     * @return a task matching the information from the line of data.
     * @throws DukeFileSystemException if the line provided does not conform to the Duke storing format.
     */
    public Task parseTaskFromLine(String line) throws DukeFileSystemException {
        String[] lineArgs = line.split(" \\| ");
        String taskType = lineArgs[0];
        boolean isDone = lineArgs[1].equals("1");
        String taskDescription = lineArgs[2];
        String date = lineArgs.length > 3 ? lineArgs[3] : "";
        Task task;
        switch (taskType) {
        case "T":
            task = new Todo(taskDescription);
            break;
        case "D":
            task = new Deadline(taskDescription, date);
            break;
        case "E":
            task = new Event(taskDescription, date);
            break;
        default:
            throw new DukeFileSystemException("Invalid data read from file while loading tasks. A new list will " +
                    "be used for this session.");
        }
        if (isDone) {
            task.markAsDone();
        }
        return task;
    }

    /**
     * Gets the type of Duke command of a given input string.
     * The Duke command of an input string is given by the first argument provided in the string.
     *
     * @param inputString the string literal of the input.
     * @return a corresponding Duke command type based on the input string.
     */
    public DukeCommand getCommandType(String inputString) {
        DukeCommand inputCommand;
        String command = inputString.split(" ", 2)[0];
        switch (command) {
        case "find":
            inputCommand = DukeCommand.FIND;
            break;
        case "done":
            inputCommand = DukeCommand.DONE;
            break;
        case "bye":
            inputCommand = DukeCommand.BYE;
            break;
        case "list":
            inputCommand = DukeCommand.LIST;
            break;
        case "todo":
            inputCommand = DukeCommand.TODO;
            break;
        case "event":
            inputCommand = DukeCommand.EVENT;
            break;
        case "deadline":
            inputCommand = DukeCommand.DEADLINE;
            break;
        case "delete":
            inputCommand = DukeCommand.DELETE;
            break;
        default:
            inputCommand = DukeCommand.INVALID;
        }

        return inputCommand;
    }

    /**
     * Gets the string literal of the arguments provided in a duke command.
     * This refers to the entire string literal after the first word provided in the input.
     *
     * @param inputString the string literal of the input.
     * @return a string literal of the input after the first word. If there is only one word, an empty string is
     * returned instead.
     */
    public String getArgsLiteral(String inputString) {
        String[] args = inputString.split(" ", 2);
        return args.length > 1 ? args[1] : "";
    }
}
