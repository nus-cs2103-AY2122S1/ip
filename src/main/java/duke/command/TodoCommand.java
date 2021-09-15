package duke.command;

import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.task.Task;
import duke.task.Todo;
import duke.util.CommandModifier;
import duke.util.ExceptionChecker;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * A class that handles todo-addition command.
 */
public class TodoCommand extends Command {

    private String[] tags;
    private String description;

    /**
     * Constructs an TodoCommand instance that handles the logic of todo-addition.
     *
     * @param description The todo description.
     * @throws EmptyDescriptionException The exception being thrown when the todo has empty description.
     */
    public TodoCommand(String description) throws EmptyDescriptionException {
        ExceptionChecker.checkEmptyDescription("todo", description);
        this.tags = CommandModifier.getTags(description);
        this.description = CommandModifier.removeTagsFrom(tags, description);
    }

    // Returns a response telling the user that the task has been successfully added and stored.
    private String createResponse(TaskList tasks, Task task) {
        String prefix = "HOHOHO. I've added this todo:\n ";
        int taskNum = tasks.getTaskNum();
        String summary = "\nNow you have " + taskNum + " tasks in the list.";

        return String.format("%s%s", prefix + task, summary);
    }

    // Returns a todo instance.
    private Todo createTodo() {
        return new Todo(tags, description);
    }

    /**
     * Returns the response after executing the task-addition command.
     *
     * @param tasks The list that stores all the tasks to be added/deleted.
     * @param ui The ui that deals with interactions with the user.
     * @param storage The storage that deals with loading tasks from the file and saving tasks in the file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Todo todo = createTodo();
            tasks.add(todo);
            storage.save(tasks);

            return createResponse(tasks, todo);
        } catch (DateTimeParseException e) {
            return new Ui().showError(e.getMessage());
        }
    }

    /**
     * Returns a boolean value of whether it is a command that exit the program.
     *
     * @return The boolean value of whether it is a command that exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
