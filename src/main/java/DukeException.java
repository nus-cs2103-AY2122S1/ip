class UnknownCommandException extends Exception {
	public UnknownCommandException() {
		super("Sorry but my database does not have such command.");
	}
}

class IllegalFormatException extends Exception {
	public IllegalFormatException(String format) {
		super("Please follow this format:\n  " + format);
	}
}

class TaskNotFoundException extends Exception {
	public TaskNotFoundException() {
		super("There is no such tasks with this task number!");
	}
}

class EmptyListException extends Exception {
	public EmptyListException() {
		super("Your list is empty! Maybe add some tasks into it?");
	}
}
