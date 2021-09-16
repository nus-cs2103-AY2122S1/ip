package command;

import duke.Duke;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import task.Task;

public class TagCommand extends Command {
    protected TagCommand(String args) {
        super(args);
    }
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage store, Duke bot) {
        String[] numTag = Parser.parseArgs(this.args, "#");
        try {
            Task t = tasklist.tagTask(Integer.valueOf(numTag[0]), numTag[1]);
            return "Tagged task number " + Integer.valueOf(numTag[0]);
        } catch (NumberFormatException e) {
            return "please input a number";
        }
    }
}
