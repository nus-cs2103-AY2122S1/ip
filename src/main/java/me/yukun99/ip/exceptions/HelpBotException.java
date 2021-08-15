package me.yukun99.ip.exceptions;

public class HelpBotException extends Exception {
	/**
	 * Constructor for a HelpBotException instance not caused by other errors.
	 *
	 * @param errorMessage Error message.
	 */
	public HelpBotException(String errorMessage) {
		super(errorMessage);
	}

	/**
	 * Constructor for a HelpBotException instance caused by other errors.
	 *
	 * @param errorMessage Error message.
	 * @param error        Error that caused this exception.
	 */
	public HelpBotException(String errorMessage, Throwable error) {
		super(errorMessage, error);
	}

	/**
	 * Returns a string representation of the HelpBotException instance.
	 *
	 * @return The string representation of the HelpBotException instance.
	 */
	public String toString() {
		if (super.getCause() == null) {
			return "";
		}
		return super.getCause().toString();
	}
}
