package yoyo.command;

import yoyo.core.DialogHandler;
import yoyo.core.Storage;
import yoyo.exception.YoyoException;
import yoyo.task.Task;
import yoyo.task.TaskList;
import yoyo.task.Todo;

public class CommandTodo extends Command {
    public CommandTodo(String[] inputTokens) {
        super(inputTokens);
    }


    /**
     * Executes "todo" command.
     *
     * @param tasks         Tasks currently in the Yoyo program.
     * @param storage       Storage instance of the Yoyo program.
     * @param dialogHandler Ui instance of Yoyo program.
     * @return The result string to be shown to user.
     * @throws YoyoException
     */
    @Override
    public String execute(TaskList tasks, Storage storage, DialogHandler dialogHandler)
            throws YoyoException {
        checkCompleteCommand(inputTokens);
        Task newTask = new Todo(inputTokens[1]);
        tasks.add(newTask);
        return dialogHandler.printAddMessage(newTask, tasks);
    }
}
