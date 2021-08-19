package duke.command;
import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.task.Task;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage store) {
        Task t;
        TaskList temp = new TaskList();
        for (int i = 0; i < tasklist.size(); i++) {
            t = tasklist.get(i);
            if (t.getLabel().contains(keyword)) {
                temp.add(t);
            }
        }
        ui.displayFindList(temp);
    }
}
