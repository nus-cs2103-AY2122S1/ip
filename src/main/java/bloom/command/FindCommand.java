package bloom.command;

import bloom.app.TaskList;
import bloom.constant.Message;
import bloom.task.Task;

public class FindCommand extends Command {
	
	private final String keyword;
	
	public FindCommand(String keyword) {
		this.keyword = keyword;
	}

	public void run() {
		System.out.println(Message.COMMAND_FIND.getMessage());
		for (int i = 0, j = 1; i < TaskList.size(); ++i) {
			Task t = TaskList.get(i);
			if (t.getDescription().contains(keyword)) {
				System.out.println("\t " + j + ". " + t);
				++j;
			}
		}
	}
}
