package me.yukun99.ip.exceptions;

public class HelpBotInvalidTaskException extends HelpBotIllegalArgumentException {
	private final String task;

	public HelpBotInvalidTaskException(Throwable error, String argument, String task) {
		super(error, argument);
		this.task = task;
	}

	public String toString() {
		return super.toString() + ", caused by:\n"
				+ "Index '" + task + "' does not correspond to a valid task, caused by:\n"
				+ super.getCause();
	}
}
