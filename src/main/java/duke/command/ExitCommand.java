package duke.command;

import java.io.*;
import java.util.*;
import duke.util.UI;
import duke.util.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class ExitCommand extends Command {
	public void execute(TaskList taskList, UI ui, Storage storage) throws IOException {
		ui.add("Bye. Hope to see you again soon!");
		ui.print();
		storage.saveTasks(taskList.getTasks());
	}

	public boolean isExit() {
		return true;
	}
}
