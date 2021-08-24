package bloom.command;

import bloom.app.TaskList;
import bloom.constant.Message;
import bloom.task.Event;

import java.time.LocalDateTime;

public class EventCommand extends Command {
	
	private final String description;
	
	private final LocalDateTime at;
	
	public EventCommand(String description, LocalDateTime at) {
		this.description = description;
		this.at = at;
	}
	
	public void run() {
		Event event = new Event(this.description, this.at);
		TaskList.add(event);
		System.out.println(Message.COMMAND_ADD.getMessage());
		System.out.println("\t   " + event + "\n");
	}
}
