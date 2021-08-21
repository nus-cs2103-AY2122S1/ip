package command;

import ui.message.ListMessage;
import tasklist.TaskList;

public class ListCommand extends Command {
    private TaskList list;

    public static ListCommand createCommand() {
        return new ListCommand();
    }

    public void execute(TaskList list) {
        this.list = list;
    }

    public ListMessage getOutputMessage() {
        return new ListMessage("Here are the tasks in your list:", this.list.toString());
    }
}
