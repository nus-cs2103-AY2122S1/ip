package bloom.command;

import bloom.constant.Message;
import bloom.task.Task;

public class ListCommand extends Command {
	
	public void run() {
		System.out.println(Message.COMMAND_LIST.getMessage());
		for (int i = 0; i < Task.tasks.size(); ++i) {
			int index = i + 1;
			Task task = Task.tasks.get(i);
			System.out.println("\t" + index + ". " + task);
		}
	}
}
