public class Parser {
    private static final String STOP_SIGNAL = "bye";

    /**
     * Parses the user's input and returns the appropriate command to be run by the
     * bot.
     * 
     * @param input
     *            The input entered by the user.
     * @return The response to be displayed.
     */
    public static Command parseInputToCommand(String input) throws DukeException {
        if (input.isEmpty()) {
            throw new InvalidArgumentException();
        } else if (input.equals(STOP_SIGNAL)) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListTasksCommand();
        }

        String[] inputs = input.split(" ", 2);
        String keyword = inputs[0];

        switch (keyword) {
        case "todo":
        case "deadline":
        case "event": {
            Parser.checkMissingArguments(inputs, String.format("The description of a %s cannot be empty.", keyword));
            Task newTask = Task.createTask(inputs);
            return new AddTaskCommand(newTask);
        }
        case "done": {
            Parser.checkMissingArguments(inputs, "Please specify a task number to mark as done.");
            int index = Integer.parseInt(inputs[1]) - 1;
            return new MarkTaskDoneCommand(index);
        }
        case "delete": {
            Parser.checkMissingArguments(inputs, "Please specify a task number for deletion.");
            int index = Integer.parseInt(inputs[1]) - 1;
            return new DeleteTaskCommand(index);
        }
        case "list": {
            Parser.checkMissingArguments(inputs, "Please specify a valid flag.");
            String remainingInput = inputs[1];
            return new ListTasksByDateCommand(remainingInput);
        }
        default: {
            throw new InvalidArgumentException();
        }
        }
    }

    public static void checkMissingArguments(String[] sections, String errorMessage) throws MissingArgumentException {
        if (sections.length != 2 || sections[1].isEmpty()) {
            throw new MissingArgumentException(errorMessage);
        }
    }
}
