package duke.Command;

import java.util.ArrayList;

import duke.*;


public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> list = tasks.getList();
        list.removeIf(task -> !task.getDescription().contains(this.keyword));
        String result = "";
        if (list.size() == 0) {
            result += ("No matching tasks found.");
        } else {
            result += (list.size() + " matching task(s):" + "\n");
            result += (new TaskList(list).allTasks());
        }
        return result;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
