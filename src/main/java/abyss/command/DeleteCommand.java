package abyss.command;

import java.io.IOException;

import abyss.Abyss;
import abyss.Ui;
import abyss.exception.InvalidCommandException;
import abyss.task.Task;

/**
 * Represents a command to delete a task from the list of tasks.
 */
public class DeleteCommand implements Command {
    private static final String DELETE_REGEX = "^\\d*$";

    private int index;

    protected DeleteCommand(String content) throws InvalidCommandException {
        if (!content.matches(DELETE_REGEX)) {
            throw new InvalidCommandException("Command 'delete' should be followed by "
                                                  + "the index of the task piece.");
        }

        int numberOfTasks = Abyss.getTaskManager().getNumberOfTasks();
        if (numberOfTasks == 0) {
            throw new InvalidCommandException("The Abyss is empty.");
        }

        int i = Integer.parseInt(content);
        if (i < 1 || i > numberOfTasks) {
            throw new InvalidCommandException("Index should be positive and not more than "
                                                  + numberOfTasks);
        }

        this.index = i - 1;
    }

    /**
     * Executes the delete command.
     *
     * @return Response from executing the delete command.
     * @throws IOException If there is error saving to file.
     */
    @Override
    public String execute() throws IOException {
        Task task = Abyss.getTaskManager().delete(index);
        Abyss.getStorage().saveTasks(Abyss.getTaskManager());
        return Ui.replyTaskDeleted(task);
    }
}

