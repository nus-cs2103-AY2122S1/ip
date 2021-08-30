package jwbot.command;

import java.io.IOException;

import jwbot.data.TaskList;
import jwbot.data.exception.JwBotException;
import jwbot.data.task.Todo;
import jwbot.storage.Storage;
import jwbot.ui.Ui;

public class AddTodoCommand extends Command {

    public AddTodoCommand(String input) {
        super(input);
    }

    /**
     * The method that executes the command
     *
     * @param tasks list of the tasks
     * @param ui the ui object responsible for user interaction
     * @param storage the storage object responsible for writing and reading txt file
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JwBotException {
        String content = input.split(" ", 2)[1];
        Todo todo = new Todo(content);
        tasks.addTask(todo);
        try {
            storage.write(tasks);
        } catch (IOException e) {
            throw new JwBotException("Sorry bro, I think you made an error with the todo format!");
        }
        return ui.showAddTaskSuccessMessage(todo);
    }

    /**
     * The method that checks if the bot should be stopped.
     *
     * @return the boolean false to not stop the bot
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
