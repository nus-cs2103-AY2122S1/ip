package chad.command;

/**
 * Helper class to parse commands.
 *
 * @author Jay Aljelo Saez Ting
 */
public class CommandParser {

    private static final String EXIT_COMMAND = "bye";
    private static final String ADD_TODO_TASK_COMMAND = "todo";
    private static final String LIST_TASKS_COMMAND = "list";
    private static final String MARK_TASK_DONE_COMMAND = "done";
    private static final String ADD_DEADLINE_TASK_COMMAND = "deadline";
    private static final String ADD_EVENT_TASK_COMMAND = "event";
    private static final String DELETE_TASK_COMMAND = "delete";
    private static final String FIND_TASKS_COMMAND = "find";
    private static final String UNDO_COMMAND = "undo";
    private static final String EMPTY_COMMAND_ERROR_MESSAGE = "This command is empty.";
    private static final String NO_EXISTING_COMMAND_ERROR_MESSAGE = "This command does not exist.";

    /**
     * Parses the command to get the Command instance.
     *
     * @param command The command.
     * @return The Command instance.
     * @throws ChadInvalidCommandException If the command is empty, it does not exist, or it is malformed.
     */
    public Command getCommandInstance(String command) throws ChadInvalidCommandException {
        String[] tokens = command.strip().split(" ");
        if (tokens.length == 0 || tokens[0].length() == 0) {
            throw new ChadInvalidCommandException(EMPTY_COMMAND_ERROR_MESSAGE);
        }
        String commandName = tokens[0];
        switch (commandName) {
        case EXIT_COMMAND:
            return new ExitCommand(command);
        case ADD_TODO_TASK_COMMAND:
            return new AddToDoTaskCommand(command);
        case LIST_TASKS_COMMAND:
            return new ListTasksCommand(command);
        case MARK_TASK_DONE_COMMAND:
            return new MarkTaskDoneCommand(command);
        case ADD_DEADLINE_TASK_COMMAND:
            return new AddDeadlineTaskCommand(command);
        case ADD_EVENT_TASK_COMMAND:
            return new AddEventTaskCommand(command);
        case DELETE_TASK_COMMAND:
            return new DeleteTaskCommand(command);
        case FIND_TASKS_COMMAND:
            return new FindTasksCommand(command);
        case UNDO_COMMAND:
            return new UndoCommand(command);
        default:
            throw new ChadInvalidCommandException(NO_EXISTING_COMMAND_ERROR_MESSAGE);
        }
    }
}
