package util.commands;

import util.tasks.*;
import util.ui.*;

public class DelCommand implements Command {
    private final int delPos;
    private final TaskList tasklist;
    //should personalize the ui for each of the commands?
    private final Ui delUI = new Ui();

    public DelCommand(int delPost, TaskList tasklist) {
        this.delPos = delPost;
        this.tasklist = tasklist;
    }



    @Override
    public void execute() {
        Task t = this.tasklist.remove(delPos);
        delUI.printTaskDel(t, tasklist.size());
    }




}
