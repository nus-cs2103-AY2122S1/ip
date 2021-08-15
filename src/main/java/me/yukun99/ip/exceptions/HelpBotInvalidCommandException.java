package me.yukun99.ip.exceptions;

public class HelpBotInvalidCommandException extends HelpBotException {
	private final String message;

	public HelpBotInvalidCommandException(String message) {
		super();
		this.message = message;
	}

	public String toString() {
		return super.toString()
				+ "\nThe following is not a command: " + message;
	}
}
