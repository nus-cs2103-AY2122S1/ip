public class CommandParser {
    public static Command parse(String commandString) throws DukeException {
        if (commandString.startsWith("list")) {
            return parseListCommand();
        } else if (commandString.startsWith("done")) {
            return parseMarkDoneCommand(commandString);
        } else if (commandString.startsWith("todo")) {
            return parseTodoCommand(commandString);
        } else if (commandString.startsWith("deadline")) {
            return parseDeadlineCommand(commandString);
        } else if (commandString.startsWith("event")) {
            return parseEventCommand(commandString);
        } else {
            throw new DukeException("Sorry, I don't understand that command...");
        }
    }

    private static Command parseListCommand() {
        return new ListCommand();
    }

    private static Command parseMarkDoneCommand(String commandString) throws DukeException {
        String[] tokens = commandString.split(" ");
        if (tokens.length <= 1) {
            throw new DukeException("Please indicate a task number to mark as done!");
        } else {
            try {
                int taskIndex = Integer.parseInt(tokens[1]) - 1;
                return new MarkDoneCommand(TaskStorage.getInstance().get(taskIndex));
            } catch (NumberFormatException e) {
                throw new DukeException("Please indicate a valid task number to mark as done!");
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("404 Task not found!");
            }
        }
    }

    private static Command parseTodoCommand(String commandString) throws DukeException {
        String payload = commandString.substring("todo".length()).trim();
        if (payload.length() <= 0) {
            throw new DukeException("Todo description cannot be empty!");
        }
        return new AddCommand(new TodoTask(payload, false));
    }

    private static Command parseDeadlineCommand(String commandString) throws DukeException {
        String payload = commandString.substring("deadline".length()).trim();
        int separatorIndex = payload.lastIndexOf("/by");
        if (separatorIndex < 0) {
            throw new DukeException("Please indicate in this format: deadline [description] /by [due date].");
        }
        String deadlineContent = payload.substring(0, separatorIndex).trim();
        String deadline = payload.substring(separatorIndex + "/by".length()).trim();
        if (deadlineContent.length() <= 0) {
            throw new DukeException("Please indicate the deadline description!");
        } else if (deadline.length() <= 0) {
            throw new DukeException("Please indicate the due date!");
        }
        return new AddCommand(new DeadlineTask(deadlineContent, false, deadline));
    }

    private static Command parseEventCommand(String commandString) throws DukeException {
        String payload = commandString.substring("event".length()).trim();
        int separatorIndex = payload.lastIndexOf("/at");
        if (separatorIndex < 0) {
            throw new DukeException("Please indicate in this format: event [description] /at [due date].");
        }
        String eventContent = payload.substring(0, separatorIndex).trim();
        String eventDate = payload.substring(separatorIndex + "/at".length()).trim();
        if (eventContent.length() <= 0) {
            throw new DukeException("Please indicate the event description!");
        } else if (eventDate.length() <= 0) {
            throw new DukeException("Please indicate the event date!");
        }
        return new AddCommand(new DeadlineTask(eventContent, false, eventDate));
    }
}
