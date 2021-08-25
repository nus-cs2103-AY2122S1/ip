public class Parser {
    public static Command parse(String input) {
        String inputCommand = input.split(" ")[0];
        String parsedMessage = input.replace(inputCommand, "").trim();

        switch (inputCommand) {
            case "bye":
                return parseByeInput();
            case "deadline":
                return parseDeadlineInput(parsedMessage);
            case "delete":
                return parseDeleteInput(parsedMessage);
            case "done":
                return parseDoneInput(parsedMessage);
            case "event":
                return parseEventInput(parsedMessage);
            case "list":
                return parseListInput();
            case "todo":
                return parseTodoInput(parsedMessage);
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static Command parseByeInput() {
        return new ExitCommand();
    }

    private static Command parseDeadlineInput(String input) {
        try {
            String taskDescription = input.replaceAll("/by.*", "");
            validateDescription(taskDescription, "deadline");
            String deadline = input.split("/by")[1];
            return new DeadlineCommand(new String[] { taskDescription, deadline });
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The end time of a deadline cannot be empty.");
        }
    }

    private static Command parseDeleteInput(String input) {
        try {
            validateDescription(input, "delete");
            int taskNo = Integer.parseInt(input) - 1;
            return new DeleteCommand(new String[] { String.valueOf(taskNo) });
        } catch (NumberFormatException e) {
            throw new DukeException("Enter a number for a delete action");
        }
    }

    private static Command parseDoneInput(String input) {
        try {
            validateDescription(input, "done");
            int taskNo = Integer.parseInt(input) - 1;
            return new DoneCommand(new String[] { String.valueOf(taskNo) });
        } catch (NumberFormatException e) {
            throw new DukeException("Enter a number for a done action!");
        }
    }

    private static Command parseEventInput(String input) {
        try {
            String taskDescription = input.replaceAll("/at.*", "");
            validateDescription(taskDescription, "event");
            String deadline = input.split("/at")[1];
            return new EventCommand(new String[] { taskDescription, deadline });
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The start and end time of a event cannot be empty.");
        }
    }

    private static Command parseListInput() {
        return new ListCommand();
    }

    private static Command parseTodoInput(String input) {
        validateDescription(input, "todo");
        return new TodoCommand(new String[] { input });
    }

    private static void validateDescription(String input, String type) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException(String.format("The description of a %s cannot be empty.", type));
        }
    }
}
