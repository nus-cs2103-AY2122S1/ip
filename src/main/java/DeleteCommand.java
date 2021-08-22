import java.io.*;
import java.util.*;

public class DeleteCommand extends Command {
	private int index;

	public DeleteCommand(int index) {
		this.index = index;
	}

	public void execute(TaskList taskList, UI ui, Storage storage) throws IOException {
		ui.add("Noted. I've removed this task:");
		ui.add("  " + taskList.get(this.index));
		taskList.delete(index);
		ui.add(String.format("Now you have %d task(s) in the list.", taskList.size()));
		ui.print();
		storage.saveTasks(taskList.getTasks());
	}
}
