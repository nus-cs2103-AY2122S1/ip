package sariel.util.commands;

import sariel.util.tasks.DateTaskTable;
import sariel.util.tasks.DatedTask;
import sariel.util.tasks.Task;
import sariel.util.tasks.TaskList;
import sariel.util.ui.Ui;

/**
 * The class representing the delete command.
 *
 */
public class DelCommand implements Command {
    private final int delPos;
    private final TaskList tasklist;
    //should personalize the ui for each of the commands?
    private final Ui delUI = new Ui();
    private final DateTaskTable table;

    /**
     * Constructor for the delete command.
     *
     * @param delPos The position to delete at.
     * @param tasklist The tasklist to delete from.
     * @param table The dateTaskTable we might delete from.
     */
    public DelCommand(int delPos, TaskList tasklist, DateTaskTable table) {
        this.delPos = delPos;
        this.tasklist = tasklist;
        this.table = table;
    }



    @Override
    public String execute() {
        //have to account for when it is a dated item.
        Task t = this.tasklist.remove(delPos - 1);
        //can use instanceof for this
        if (t.isDated()) {
            DatedTask dt = (DatedTask) t;
            dt.removeFromTable(table);
        }
        return delUI.getOutputFromTaskDeleted(t, tasklist.size());

    }




}
