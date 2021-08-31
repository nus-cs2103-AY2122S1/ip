package commands;

import tasks.Task;
import viper.Storage;
import viper.TaskList;
import viper.Ui;

import java.io.IOException;

public class DoneCommand extends Command {
    String cmdLine;
    int index;
    
    public DoneCommand(String cmdLine) {
        this.cmdLine = cmdLine;
        String[] splitLine = cmdLine.split(" ", 2);
        index = Integer.parseInt(splitLine[1]);
    }
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (index < tasks.getSize() + 1) {
            Task curr = tasks.getTask(index - 1);
            storage.doneTask(curr);
            tasks.doneTask(index - 1);
            String[] msg = {"Good job on completing your task!!!", "I've marked this task as done:", curr.toString()};
            ui.showMessage(msg);
        } else {
            ui.showInvalidIndexError();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
