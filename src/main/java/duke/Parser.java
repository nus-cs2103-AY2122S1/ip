package duke;

/**
 * Encapsulates a Parser class. Parses user input into commands.
 */
public class Parser {
    private final TaskList tasks;

    /**
     * Constructs a Parser.
     *
     * @param tasks TaskList to send commands to.
     */
    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Parses a command and manipulates the TaskList accordingly.
     *
     * @param command String representation of command.
     * @return An array of message strings.
     * @throws DukeException Thrown when an invalid command is given.
     */
    public String[] parseCommand(String command) throws DukeException {
        if (command.equals("bye")) {
            return parseByeCommand();
        } else if (command.equals("list")) {
            return parseListCommand();
        } else if (command.startsWith("find ")) {
            return parseFindCommand(command);
        } else if (command.startsWith("done ")) {
            return parseDoneCommand(command);
        } else if (command.startsWith("delete ")) {
            return parseDeleteCommand(command);
        } else if (command.startsWith("todo")) {
            return parseTodoCommand(command);
        } else if (command.startsWith("deadline")) {
            return parseDeadlineCommand(command);
        } else if (command.startsWith("event")) {
            return parseEventCommand(command);
        } else {
            return parseUnknownCommand();
        }
    }

    /**
     * Parses a bye command.
     *
     * @return A bye message which will terminate the program.
     */
    private String[] parseByeCommand() {
        return new String[] {Ui.BYE_MSG};
    }

    /**
     * Parses a list command.
     *
     * @return String array of task list.
     */
    private String[] parseListCommand() {
        return tasks.getTaskStrings();
    }

    /**
     * Parses a find command.
     *
     * @param command Input from user.
     * @return String array of matching tasks.
     */
    private String[] parseFindCommand(String command) {
        return tasks.findMatchingTasks(command.substring(5));
    }

    /**
     * Parses a done command.
     *
     * @param command Input from user.
     * @return Affirmation of completion of task.
     * @throws DukeException If index out of range.
     */
    private String[] parseDoneCommand(String command) throws DukeException {
        int cutOffIndex = "done ".length();
        return tasks.markTask(Integer.parseInt(command.substring(cutOffIndex)) - 1);
    }

    /**
     * Parses a delete command.
     *
     * @param command Input from user.
     * @return Affirmation of deletion of task.
     * @throws DukeException If index out of range.
     */
    private String[] parseDeleteCommand(String command) throws DukeException {
        int cutOffIndex = "delete ".length();
        return tasks.deleteTask(Integer.parseInt(command.substring(cutOffIndex)) - 1);
    }

    /**
     * Parses a todo command.
     *
     * @param command Input from user.
     * @return Affirmation of todo created.
     * @throws DukeException if invalid description.
     */
    private String[] parseTodoCommand(String command) throws DukeException {
        int cutOffIndex = "todo ".length();
        if (command.length() <= cutOffIndex) {
            throw new DukeException("Don't give me an empty todo!");
        }
        return tasks.addTask(new Todo(command.substring(cutOffIndex)));
    }

    /**
     * Parses a deadline command.
     *
     * @param command Input from user.
     * @return Affirmation of deadline created.
     * @throws DukeException if invalid description or time.
     */
    private String[] parseDeadlineCommand(String command) throws DukeException {
        int cutOffIndex = "deadline ".length();
        if (command.length() <= cutOffIndex) {
            throw new DukeException("Don't give me an empty deadline!");
        }
        int byIndex = command.indexOf(" /by");
        if (byIndex == -1) {
            throw new DukeException("/by not found.");
        }
        int timeIndex = byIndex + " /by ".length();
        return tasks.addTask(new Deadline(
                command.substring(cutOffIndex, byIndex),
                command.substring(timeIndex)));
    }

    /**
     * Parses an event command.
     * @param command Input from user.
     * @return Affirmation of event created.
     * @throws DukeException if invalid description or time.
     */
    private String[] parseEventCommand(String command) throws DukeException {
        int cutOffIndex = "event ".length();
        if (command.length() <= cutOffIndex) {
            throw new DukeException("Don't give me an empty event!");
        }
        int atIndex = command.indexOf(" /at");
        if (atIndex == -1) {
            throw new DukeException("/at not found.");
        }
        int timeIndex = atIndex + " /at ".length();
        return tasks.addTask(new Event(
                command.substring(cutOffIndex, atIndex),
                command.substring(timeIndex)));
    }

    /**
     * Parses an unknown command.
     *
     * @return Does not return any valid response.
     * @throws DukeException whenever an unknown command is detected.
     */
    private String[] parseUnknownCommand() throws DukeException {
        throw new DukeException("What on earth does that mean? Are you okay?");
    }
}
