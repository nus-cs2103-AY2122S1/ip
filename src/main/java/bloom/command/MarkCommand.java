package bloom.command;

import bloom.app.TaskList;
import bloom.constant.Message;
import bloom.task.Task;

public class MarkCommand extends Command {
	
	private final int index;
	
	public MarkCommand(int index) {
		this.index = index;
	}

	@Override
	public void run() {
		Task task = TaskList.get(this.index - 1);
		task.markAsDone();
		System.out.println(Message.COMMAND_MARK.getMessage());
		System.out.println(task);
	}
}
