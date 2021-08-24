package bloom.constant;

public enum Message {
	
	COMMAND_GREET("\t Hello! I'm Bloom\n\t What can I do for you?\n"), 
	COMMAND_EXIT("\t Bye. Hope to see you again soon!\n"), 
	COMMAND_LIST("\t Here are the tasks in your list:"), 
	COMMAND_FIND("\t Here are the matching tasks in your list:"), 
	COMMAND_ADD("\t Got it. I've added this task:"), 
	COMMAND_DELETE("\t Noted. I've removed this task:"), 
	COMMAND_MARK("\t Nice! I've marked this task as done:"), 
	
	EXCEPTION_IO("\t Something is wrong with Bloom memory :(\n"), 
	EXCEPTION_UNKNOWN_COMMAND("\t This command is not supported.\n");

	private final String message;
	
	Message(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
