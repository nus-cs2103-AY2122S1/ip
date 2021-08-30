package botto.command;

import botto.BottoException;
import botto.task.Task;
import botto.util.Storage;
import botto.util.TaskList;
import botto.util.Ui;

/**
 * Command for marking a task as done
 */
public class MarkDoneCommand implements Command {
    private String command;

    /**
     * Constructor for a MarkDoneCommand
     *
     * @param command user input
     */
    public MarkDoneCommand(String command) {
        this.command = command;
    }

    /**
     * mark the task as done, update the storage and print relevant messages
     *
     * @param taskList the task list involved
     * @param ui the ui of the Botto bot
     * @param storage storage of the Botto bot
     * @throws BottoException when the task is not specified
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws BottoException {
        String integer = command.replaceAll("\\D+", "");
        int index;

        try {
            index = Integer.parseInt(integer) - 1;
        } catch (Exception e) {
            throw new BottoException("☹ OOPS!!! You have to specify the task.");
        }

        Task task = taskList.markAsDone(index);

        ui.respondDone(task);
        storage.save(taskList.getTasks());

    }
}
