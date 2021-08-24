package duke.command;

import duke.Duke;
import duke.task.TaskList;
import duke.Ui;

import java.util.Scanner;

/**
 * Represents the general list command.
 */
public class ListCommand extends Command {
    public ListCommand(Duke duke, Scanner sc) {
        super(duke, sc);
    }

    @Override
    public void execute(TaskList taskList) {
        Ui.printReply(taskList.toString());
    }
}
