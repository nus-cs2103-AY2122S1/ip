package Yoyo.command;

import Yoyo.core.Storage;
import Yoyo.core.Ui;
import Yoyo.exception.YoyoException;
import Yoyo.task.Task;
import Yoyo.task.TaskList;
import Yoyo.task.Todo;

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
