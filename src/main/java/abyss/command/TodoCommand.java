package abyss.command;

import java.io.IOException;

import abyss.Abyss;
import abyss.Ui;
import abyss.exception.InvalidTodoException;
import abyss.task.Task;

/**
 * Represents a command to add a to-do task to the list of tasks.
 */
public class TodoCommand implements Command {
    private String description;

    protected TodoCommand(String description) throws InvalidTodoException {
        if (description.equals("")) {
            throw new InvalidTodoException();
        }
        this.description = description;
    }

    /**
     * Executes the add to-do command.
     *
     * @return Response from executing the add to-do command.
     * @throws IOException If there is error saving to file.
     */
    @Override
    public String execute() throws IOException {
        Task task = Abyss.getTaskManager().addToDo(description);
        String response = Ui.replyTaskAdded(task);
        Abyss.getStorage().saveTasks(Abyss.getTaskManager());
        return response;
    }
}
