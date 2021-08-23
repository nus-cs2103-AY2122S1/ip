package jwbot.command;

import jwbot.data.TaskList;
import jwbot.data.exception.JWBotException;
import jwbot.data.task.Todo;
import jwbot.storage.Storage;
import jwbot.ui.Ui;

import java.io.IOException;

public class AddTodoCommand extends Command {

    public AddTodoCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JWBotException {
        String content = input.split(" ", 2)[1];
        Todo todo = new Todo(content);
        tasks.addTask(todo);
        try {
            storage.write(tasks);
        } catch (IOException e) {
            throw new JWBotException("Sorry bro, I think you made an error with the todo format!");
        }
        ui.showAddTaskSuccessMessage(todo);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
