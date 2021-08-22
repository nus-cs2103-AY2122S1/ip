package util.commands;

import util.tasks.*;
import util.ui.*;

public class DelCommand implements Command {
    private final int delPos;
    private final TaskList tasklist;
    //should personalize the ui for each of the commands?
    private final Ui delUI = new Ui();
    private final DateTaskTable table;

    public DelCommand(int delPost, TaskList tasklist, DateTaskTable table) {
        this.delPos = delPost;
        this.tasklist = tasklist;
        this.table = table;
    }



    @Override
    public void execute() {
        //have to account for when it is a dated item.
        Task t = this.tasklist.remove(delPos - 1);
        //can use instanceof for this
        if (t.isDated()) {
            DatedTask dt = (DatedTask) t;
            dt.removeFromTable(table);
        }
        delUI.printTaskDel(t, tasklist.size());
    }




}
