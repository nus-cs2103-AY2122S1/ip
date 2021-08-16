import java.util.*;

/* Exception to handle correct task name-input, but failed on subsequent syntax */
public class TaskException extends DukeException {
	private Task task;

	public TaskException(Task task) {
		super(String.format("Invalid %s-task creation syntax.", task.getType()));
		this.task = task;
	}

	public List<String> getHelpMessages() {
		List<String> list = new ArrayList<>();
		list.add(String.format("  Use the following format to create %s tasks.", task.getType()));
		list.add("  \"" + task.getFormat() + "\"");
		list.add("  Replace {}-values with suitable non-empty descriptors.");
		return list;
	}
}