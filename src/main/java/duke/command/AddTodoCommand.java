package duke.command;

import duke.task.Task;
import duke.task.Todo;

public class AddTodoCommand extends AddCommand {
    public static final String COMMAND_IDENTIFIER = "todo";

    public static Command create(String userInput) throws MalformedCommandException {
        try {
            String description = userInput.split(" ", 2)[1];
            Task task = new Todo(description);
            return new AddTodoCommand(task);
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            throw new MalformedCommandException("Creating an todo needs to follow the following format: " +
                "todo [description]");
        }
    }

    private AddTodoCommand(Task task) {
        this.task = task;
    }
}
