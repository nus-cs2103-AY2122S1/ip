package duke.command;

import duke.Duke;
import duke.GUI;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(Duke duke, String keyword) {
        super(duke);
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList) {
        TaskList tasksFound = this.duke.findTasks(this.keyword);
        return GUI.printFindMessage(tasksFound);
    }
}
