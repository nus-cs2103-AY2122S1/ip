package commands;

import tasks.Task;
import viper.Storage;
import viper.TaskList;
import viper.Ui;

public class ListCommand extends Command {
    
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getSize() > 0) {
            String[] msg = {"Ok!! You wanna know what you have on your list?", "I got you covered!!",
                    "Here are all the task(s) in your list"};
            ui.showMessage(msg);
            for (int i = 0; i < tasks.getSize(); i++) {
                int taskNo = i + 1;
                Task curr = tasks.getTask(i);
                ui.printLine(taskNo + "." + curr.toString());
            }
            ui.printDiv();
        } else {
            String[] msg = {"WOW! There's nothing on your list.", "You can add items to your list whenever you like!"};
            ui.showMessage(msg);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
