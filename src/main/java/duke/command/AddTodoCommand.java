package duke.command;

import duke.parser.Parser;
import duke.task.Task;
import duke.task.Todo;

/**
 * Adds a todo task to the list of user's tasks.
 */
public class AddTodoCommand extends AddCommand {
    public static final String COMMAND_IDENTIFIER = "todo";

    /**
     * Returns the AddTodo command represented by the user input.
     *
     * @param userInput String input provided by the user.
     * @return AddTodo user command.
     * @throws MalformedCommandException If userInput is incorrectly formatted for an AddTodo command.
     */
    public static Command create(String userInput) throws MalformedCommandException {
        assert userInput != null : "User input should be null for the creation of a Command";

        try {
            String commandParams = Parser.getCommandParams(userInput);
            String taskDescription = getTaskDescription(commandParams);
            Task task = new Todo(taskDescription);
            return new AddTodoCommand(task);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new MalformedCommandException("Creating an todo needs to follow the following "
                    + "format: todo [description]");
        }
    }

    private AddTodoCommand(Task task) {
        this.task = task;
    }
}
