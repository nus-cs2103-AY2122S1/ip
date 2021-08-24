package duke.command;

import duke.Duke;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

import java.util.ArrayList;
import java.util.Scanner;

public class FindCommand extends Command {
    private final String keyword;

    public FindCommand(Duke duke, Scanner sc, String keyword) {
        super(duke, sc);
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList) {
        TaskList tasksFound = this.duke.findTasks(this.keyword);
        Ui.printFindMessage(tasksFound);
    }
}
