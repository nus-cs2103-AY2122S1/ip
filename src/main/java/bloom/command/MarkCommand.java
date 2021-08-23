package bloom.command;

import bloom.constant.Message;
import bloom.task.Task;

public class MarkCommand extends Command {
	
	private final int index;
	
	public MarkCommand(int index) {
		this.index = index;
	}
	
	public void run() {
		Task task = Task.tasks.get(this.index);
		task.markAsDone();
		System.out.println(Message.COMMAND_MARK.getMessage());
		System.out.println(task);
	}
}
