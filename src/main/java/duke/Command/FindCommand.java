package duke.Command;

import duke.*;

import java.util.ArrayList;

public class FindCommand extends Command{
    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> list = tasks.getList();
        list.removeIf(task -> !task.getDescription().contains(this.keyword));
        if (list.size() == 0) {
            ui.print("No matching tasks found.");
        } else {
            ui.print(list.size() + " matching task(s):");
            ui.print(new TaskList(list).allTasks());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
