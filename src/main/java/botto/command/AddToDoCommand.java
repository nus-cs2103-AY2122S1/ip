package botto.command;

import botto.BottoException;
import botto.task.Task;
import botto.task.Todo;
import botto.util.Dialog;
import botto.util.Storage;
import botto.util.TaskList;

/**
 * Command for adding an event.
 */
public class AddToDoCommand implements Command {

    private String command;

    public AddToDoCommand(String command) {
        this.command = command;
    }

    /**
     * Adds task to the tasklist, update the storage and print relevant messages.
     *
     * @param taskList the task list involved.
     * @param dialog the ui of the Botto bot.
     * @param storage storage of the Botto bot.
     * @throws BottoException when the there is no description inserted.
     */
    @Override
    public void execute(TaskList taskList, Dialog dialog, Storage storage) throws BottoException {
        String description;

        try {
            description = command.split(" ", 2)[1];
        } catch (Exception e) {
            throw new BottoException("☹ OOPS!!! The description of a todo cannot be empty.");
        }

        Task task = new Todo(description);
        taskList.addTask(task);

        dialog.respondAdd(task, taskList.getSize());
        storage.save(taskList.getTasks());
    }
}
