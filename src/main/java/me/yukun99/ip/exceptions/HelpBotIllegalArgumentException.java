package me.yukun99.ip.exceptions;

public class HelpBotIllegalArgumentException extends HelpBotException {
	protected final String argument;

	public HelpBotIllegalArgumentException(Throwable error, String argument) {
		super(error);
		this.argument = argument;
	}

	public HelpBotIllegalArgumentException(String argument) {
		super();
		this.argument = argument;
	}

	public String toString() {
		return super.toString()
				+ "\nThe following argument is invalid: " + argument;
	}
}
