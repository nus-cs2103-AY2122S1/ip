package me.yukun99.ip.exceptions;

public class HelpBotDateTimeFormatException extends HelpBotException {
	private String argument;

	public HelpBotDateTimeFormatException(String argument) {
		super();
		this.argument = argument;
	}

	@Override
	public String toString() {
		return super.toString()
				+ "\nThe following is not a valid date/time format: " + argument;
	}
}
