package bloom.command;

import bloom.app.TaskList;
import bloom.constant.Message;
import bloom.task.Task;

public class DeleteCommand extends Command {
	
	private final int index;
	
	public DeleteCommand(int index) {
		this.index = index;
	}

	@Override
	public void run() {
		Task task =TaskList.delete(this.index);
		System.out.println(Message.COMMAND_DELETE.getMessage());
		System.out.println("\t   " + task + "\n");
	} 
}
