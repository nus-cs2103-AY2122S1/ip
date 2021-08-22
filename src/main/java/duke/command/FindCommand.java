package duke.command;

import java.io.*;
import java.util.*;
import duke.util.UI;
import duke.util.Storage;
import duke.task.Task;
import duke.task.TaskList;

public class FindCommand extends Command {
	private String pattern;

	public FindCommand(String pattern) {
		this.pattern = pattern;
	}

	public void execute(TaskList taskList, UI ui, Storage storage) throws IOException {
		List<Task> matches = new ArrayList<>();
		for (Task task : taskList.getTasks()) {
			String description = task.getDescription();
			int n = description.length();
			int m = pattern.length();
			if (isSubSequence(pattern, description, m, n)) {
				matches.add(task);
			}
		}
		if (matches.isEmpty()) {
			ui.add("Sorry, there are no matches for tasks with \"" + pattern + "\".");
		} else {
			ui.add("Here are the matching tasks in your list:");
			int counter = 1;
			for (Task task : matches) {
				ui.add("" + counter + "." + task);
				counter++;
			}
		}
		ui.print();
		storage.saveTasks(taskList.getTasks());
	}

	private boolean isSubSequence(String pattern, String description, int m, int n) {
		if (m == 0) {
			return true;
		}
		if (n == 0) {
			return false;
		}
		if (pattern.charAt(m - 1) == description.charAt(n - 1)) {
			return isSubSequence(pattern, description, m - 1, n - 1);
		} else {
			return isSubSequence(pattern, description, m, n - 1);
		}
	}
}
