import java.io.*;
import java.util.*;

public class MarkDoneCommand extends Command {
	private int index;

	public MarkDoneCommand(int index) {
		this.index = index;
	}

	public void execute(TaskList taskList, UI ui, Storage storage) throws IOException {
		taskList.markDone(index);
		ui.add("Nice! I've marked this task as done:");
		ui.add("  " + taskList.get(index));
		ui.print();
		storage.saveTasks(taskList.getTasks());
	}
}