package botto.command;

import botto.*;
import botto.task.Task;
import botto.util.Storage;
import botto.util.TaskList;
import botto.util.Ui;

public class MarkDoneCommand implements Command{
    private String command;

    public MarkDoneCommand(String command) {
        this.command = command;
    }

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
