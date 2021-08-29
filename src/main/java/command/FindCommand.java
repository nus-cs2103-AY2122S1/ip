package command;

import exception.DukeException;
import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

import java.util.ArrayList;
import java.util.Locale;

public class FindCommand extends Command {

    private String keyword;
    private final boolean EXIT = false;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        System.out.println("Here are the matching tasks in your list:");
        ArrayList<Task> taskList = tasks.getTaskList();
        int index = 1;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().toLowerCase(Locale.ROOT).contains(keyword.toLowerCase(Locale.ROOT))) {
                System.out.printf("\t%d.%s%n", index, taskList.get(i));
                index++;
            }
        }
    }

    @Override
    public boolean isExit() {
        return EXIT;
    }
}
