package commands;

import viper.Storage;
import viper.TaskList;
import viper.Ui;
import tasks.Task;

import java.io.IOException;

public class DeleteCommand extends Command {
    String t;
    String[] s;
    int index;

    public DeleteCommand(String t) {
        this.t = t;
        this.s = t.split(" ", 2);
        this.index = Integer.parseInt(s[1]);
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (index < tasks.getSize() + 1) {
            Task deleteTask = tasks.getTask(index - 1);
            tasks.deleteTask(index - 1);
            storage.deleteTask(deleteTask);
            String[] msg = {"OK!! I have removed the following task from your list: " + deleteTask,
                    "Now you have " + tasks.getSize() + " task(s) left~ Yay!!!!!"};
            ui.showMessage(msg);
        }
        else {
            ui.showInvalidIndexError();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
