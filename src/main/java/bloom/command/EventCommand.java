package bloom.command;

import bloom.constant.Message;
import bloom.task.Event;
import bloom.task.Task;

public class EventCommand extends Command {
	
	private final String description;
	
	private final String at;
	
	public EventCommand(String description, String at) {
		this.description = description;
		this.at = at;
	}
	
	public void run() {
		Event event = new Event(this.description, this.at);
		Task.tasks.add(event);
		System.out.println(Message.COMMAND_ADD.getMessage());
		System.out.println("\t   " + event + "\n");
	}
}
