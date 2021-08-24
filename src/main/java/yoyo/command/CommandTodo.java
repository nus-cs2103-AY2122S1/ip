package yoyo.command;

import yoyo.core.Storage;
import yoyo.core.Ui;
import yoyo.exception.YoyoException;
import yoyo.task.Task;
import yoyo.task.TaskList;
import yoyo.task.Todo;

public class CommandTodo extends Command {
    public CommandTodo(String[] inputTokens) {
        super(inputTokens);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui)
            throws YoyoException {
        checkCompleteCommand(inputTokens);
        Task newTask = new Todo(inputTokens[1]);
        tasks.add(newTask);
        ui.printAddMessage(newTask, tasks);
    }
}
