package bloom.command;

import bloom.constant.Message;
import bloom.task.Deadline;
import bloom.task.Task;

public class DeadlineCommand extends Command {
	
	private final String description;
	
	private final String by;
	
	public DeadlineCommand(String description, String by) {
		this.description = description;
		this.by = by;
	}
	
	public void run() {
		Deadline deadline = new Deadline(this.description, this.by);
		Task.tasks.add(deadline);
		System.out.println(Message.COMMAND_ADD.getMessage());
		System.out.println("\t   " + deadline + "\n");
	}
}
