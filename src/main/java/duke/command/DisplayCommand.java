package duke.command;

import java.io.*;
import java.util.*;
import duke.util.UI;
import duke.util.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class DisplayCommand extends Command {
	public void execute(TaskList taskList, UI ui, Storage storage) throws IOException {
		if (taskList.isEmpty()) {
			ui.add("Your list of tasks is empty!");
		} else {
			ui.add("Here are the tasks in your list:");
			int counter = 1;
			for (Task task : taskList.getTasks()) {
				ui.add("" + counter + "." + task);
				counter++;
			}
		}
		ui.print();
		storage.saveTasks(taskList.getTasks());
	}
}
