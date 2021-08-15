package me.yukun99.ip.exceptions;

public class HelpBotIllegalCommandException extends HelpBotException {
	private String message;

	public HelpBotIllegalCommandException(String message) {
		super();
		this.message = message;
	}

	public String toString() {
		return super.toString()
				+ "\nThe following is not a command: " + message;
	}
}
