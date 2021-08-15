package me.yukun99.ip.exceptions;

public class HelpBotInvalidTaskException extends HelpBotException {
	private final String task;

	public HelpBotInvalidTaskException(String errorMessage, Throwable error, String task) {
		super(errorMessage, error);
		this.task = task;
	}

	public String toString() {
		return "'" + task + "' does not correspond to a valid task, caused by:\n"
				+ super.toString();
	}
}
