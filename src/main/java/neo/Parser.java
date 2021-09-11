package neo;

/**
 *  This class makes sense of the user inputs.
 *  Supported Commands: Todo, Event, Deadline, Delete, Done, List.
 *
 * @author Ryan Tian Jun.
 */
public class Parser {
    private int taskNumber;
    private String query;
    private final Command commandType;

    protected enum Command {
        TODO, EVENT, DEADLINE, DELETE, DONE, LIST, FIND, OTHER
    }

    /**
     * This constructor initializes the Parser, which parses all user commands
     * and returns the result (which command it is).
     */
    public Parser(String command) throws NeoException {
        if (isList(command)) {
            this.commandType = Command.LIST;
        } else if (isDone(command)) {
            this.commandType = Command.DONE;
            taskNumber = Integer.parseInt(command.substring(5));
        } else if (isToDo(command)) {
            this.commandType = Command.TODO;
        } else if (isDeadLine(command)) {
            this.commandType = Command.DEADLINE;
        } else if (isEvent(command)) {
            this.commandType = Command.EVENT;
        } else if (isDelete(command)) {
            this.commandType = Command.DELETE;
        } else if (isFind(command)) {
            this.commandType = Command.FIND;
        } else {
            this.commandType = Command.OTHER;
        }
    }

    /**
     * Returns the CommandType of the Command to the UI to handle
     * and execute the Command.
     *
     * @return returns CommandType.
     */
    public Command getCommandType() {
        return commandType;
    }

    /**
     * Returns the TaskNumber of the Command (if the command requires it)
     * Commands that require Task number: Delete, Done.
     *
     * @return returns Task number.
     */
    public int getTaskNumber() {
        assert taskNumber > 0 : "Task number should always be greater than 0";
        return taskNumber;
    }

    public String getQuery() {
        assert !query.equals("") : "Query should never be empty!";
        return query;
    }

    // checks if command given is a list
    private boolean isList(String command) {
        return command.equals("list");
    }

    /**
     * Checks if it is a 'Done' command.
     *
     * @param command Takes in command line input to process.
     * @return returns true if it is said command.
     * @throws NeoException Either a syntax error or lack of number provided.
     */
    private boolean isDone(String command) throws NeoException {
        if (command.length() >= 4 && command.startsWith("done")) {
            if (command.length() == 4) {
                throw new NeoException("The Done command must be followed by a number!");
            } else {
                if (isNumeric(command.substring(5))) {
                    return true;
                } else {
                    throw new NeoException("The Done command requires a number, separated by whitespace!");
                }
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if it is a 'Todo' command.
     *
     * @param command Takes in command line input to process.
     * @return returns true if it is said command.
     * @throws NeoException Either a syntax error or lack of description.
     */
    private boolean isToDo(String command) throws NeoException {
        if (command.length() >= 4 && command.startsWith("todo")) {
            if (command.length() == 4) {
                throw new NeoException("ToDo", 1);
            } else if (command.charAt(4) != ' ') {
                throw new NeoException("ToDo", 0);
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if it is a 'Deadline' command.
     *
     * @param command Takes in command line input to process.
     * @return returns true if it is said command.
     * @throws NeoException Either a syntax error or lack of description.
     */
    private boolean isDeadLine(String command) throws NeoException {
        if (command.length() >= 8 && command.startsWith("deadline")) {
            if (command.length() == 8) {
                throw new NeoException("Deadline", 1);
            } else if (command.charAt(8) != ' ') {
                throw new NeoException("Deadline", 0);
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if it is an 'Event' command.
     *
     * @param command Takes in command line input to process.
     * @return returns true if it is said command.
     * @throws NeoException Either a syntax error or lack of description.
     */
    private boolean isEvent(String command) throws NeoException {
        if (command.length() >= 5 && command.startsWith("event")) {
            if (command.length() == 5) {
                throw new NeoException("Event", 1);
            } else if (command.charAt(5) != ' ') {
                throw new NeoException("Event", 0);
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if it is a 'Delete' command.
     *
     * @param command Takes in command line input to process.
     * @return returns true if it is said command.
     * @throws NeoException Syntax errors, spacing.
     */
    private boolean isDelete(String command) throws NeoException {
        if (command.length() >= 6 && command.startsWith("delete")) {
            if (command.length() == 6) {
                throw new NeoException("The Delete command must be followed by a number!");
            } else {
                if (command.charAt(6) != ' ') {
                    throw new NeoException("The Delete command requires a number, separated by whitespace!");
                } else if (isNumeric(command.substring(7))) {
                    taskNumber = Integer.parseInt(command.substring(7));
                    return true;
                } else {
                    throw new NeoException("The Delete command requires a number, separated by whitespace!");
                }
            }
        } else {
            return false;
        }
    }

    /**
     * Checks if it is a 'Find' command.
     *
     * @param command Takes in command line input to process.
     * @return returns true if it is said command.
     * @throws NeoException Syntax errors, spacing.
     */
    private boolean isFind(String command) throws NeoException {
        int commandLength = command.length();
        if (commandLength >= 4 && command.startsWith("find")) {
            if (commandLength == 4) {
                throw new NeoException("The Find command must be followed by a string!");
            } else if (command.charAt(4) != ' ') {
                throw new NeoException("The Find command requires a string, separated by whitespace!");
            } else {
                query = command.substring(5).trim();
                return true;
            }
        } else {
            return false;
        }
    }

    // Checks if String is numeric
    private static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
