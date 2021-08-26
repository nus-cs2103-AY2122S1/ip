package duke;

public class CommandParser extends Parser<String[]> {
	private static final String[] COMMANDS_WITH_ARGS = new String[]{"todo", "deadline",
			"event", "delete", "done"};
	private static final String[] COMMANDS_WITHOUT_ARGS = new String[]{"list", "bye"};
	private static final String UNKNOWN_COMMAND_MSG = "☹ OOPS!!! " +
			"I'm sorry, but I don't know what that means :-(";
	private static final String MISSING_ARGUMENT_TEMPLATE = "☹ OOPS!!! " +
			"Seems like there are missing argument(s) for %s";
	private static final String NON_NUMBER_ARGUMENT_TEMPLATE = "☹ OOPS!!! " +
			"Should have entered a number for %s, BOI";


	public String[] parse(String cmd) throws DukeException {
		cmd = cmd.trim();
		boolean isInvalidCommand = true;

		String[] cmdSplit = cmd.split("[ \\t]+", 2);
		for (String cmdCheck : COMMANDS_WITHOUT_ARGS) {
			if (cmd.equals(cmdCheck)) {
				return new String[]{cmd};
			} else if (cmdSplit[0].equals(cmdCheck)) {
				throw new DukeException("☹ OOPS!!! Unknown Argument for " + cmdCheck);
			}
		}

		for (String cmdCheck : COMMANDS_WITH_ARGS) {
			isInvalidCommand = isInvalidCommand && !(cmdSplit[0].equals(cmdCheck));
		}

		if (isInvalidCommand) {
			throw new DukeException(UNKNOWN_COMMAND_MSG);
		} else if (cmdSplit.length != 2) {
			throw new DukeException(String.format(MISSING_ARGUMENT_TEMPLATE, cmdSplit[0]));
		} else if ((cmdSplit[0].equals("delete") || cmdSplit[0].equals("done"))
				&& !cmdSplit[1].matches("[0-9]+")) {
			throw new DukeException(String.format(NON_NUMBER_ARGUMENT_TEMPLATE, cmdSplit[0]));
		}

		switch (cmdSplit[0]) {
		case "todo":
		case "done":
		case "delete":
			return cmdSplit;
		case "deadline":
			String[] deadlineArgs = cmdSplit[1].split("[ \\t]+/by[ \\t]+", 2);
			if (deadlineArgs.length < 2) {
				throw new DukeException(UNKNOWN_COMMAND_MSG);
			} else {
				return new String[]{cmdSplit[0], deadlineArgs[0], deadlineArgs[1]};
			}
		case "event":
			String[] eventArgs = cmdSplit[1].split("[ \\t]+/at[ \\t]+", 2);
			if (eventArgs.length < 2) {
				throw new DukeException(UNKNOWN_COMMAND_MSG);
			} else {
				return new String[]{cmdSplit[0], eventArgs[0], eventArgs[1]};
			}
		}

		return new String[]{};
	}
}
