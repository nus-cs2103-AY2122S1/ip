package botto.command;

import botto.BottoException;
import botto.task.Task;
import botto.util.Dialog;
import botto.util.Storage;
import botto.util.TaskList;

/**
 * Command for deleting a task.
 */
public class DeleteCommand implements Command {
    private String command;

    /**
     * Constructor for a DeleteCommand.
     *
     * @param command user input.
     */
    public DeleteCommand(String command) {
        this.command = command;
    }

    /**
     * Delete the task from the tasklist, update the storage and print relevant messages.
     *
     * @param taskList the task list involved.
     * @param dialog the ui of the Botto bot.
     * @param storage storage of the Botto bot.
     * @throws BottoException when the task is not specified.
     */
    @Override
    public void execute(TaskList taskList, Dialog dialog, Storage storage) throws BottoException {
        String integer = command.replaceAll("\\D+", "");
        int index;

        try {
            index = Integer.parseInt(integer) - 1;
        } catch (Exception e) {
            throw new BottoException("☹ OOPS!!! You have to specify the task.");
        }

        Task task = taskList.deleteTask(index);
        int size = taskList.getSize();
        dialog.respondDelete(task, size);

        storage.save(taskList.getTasks());
    }
}
