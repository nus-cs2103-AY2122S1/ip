package bloom.command;

import bloom.constant.Message;
import bloom.task.Task;
import bloom.task.ToDo;

public class ToDoCommand extends Command {
	
	private final String description;
	
	public ToDoCommand(String description) {
		this.description = description;
	}
	
	public void run() {
		ToDo todo = new ToDo(this.description);
		Task.tasks.add(todo);
		System.out.println(Message.COMMAND_ADD.getMessage());
		System.out.println("\t   " + todo + "\n");
	}
}
