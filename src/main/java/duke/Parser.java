package duke;

/**
 *  This class makes sense of the user inputs.
 *  Supported Commands: Todo, Event, Deadline, Delete, Done, List.
 *
 * @author Ryan Tian Jun.
 */
public class Parser {
    private String currentCommand;
    private int taskNumber;
    private String query;
    private COMMAND commandType;

    protected enum COMMAND {
        TODO, EVENT, DEADLINE, DELETE, DONE, LIST, FIND, OTHER
    }

    public Parser(String command) throws DukeException {
        this.currentCommand = command;
        if (isList(command)) {
            this.commandType = COMMAND.LIST;
        } else if (isDone(command)) {
            this.commandType = COMMAND.DONE;
            taskNumber = Integer.parseInt(command.substring(5));
        } else if (isToDo(command)) {
            this.commandType = COMMAND.TODO;
        } else if (isDeadLine(command)) {
            this.commandType = COMMAND.DEADLINE;
        } else if (isEvent(command)) {
            this.commandType = COMMAND.EVENT;
        } else if (isDelete(command)) {
            this.commandType = COMMAND.DELETE;
        } else if (isFind(command)){
            this.commandType = COMMAND.FIND;
        } else {
            this.commandType = COMMAND.OTHER;
        }
    }

    public COMMAND getCommandType() {
        return commandType;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public String getQuery() {
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
     * @throws DukeException Either a syntax error or lack of number provided.
     * @return returns true if it is said command.
     */
    private boolean isDone(String command) throws DukeException {
        if (command.length() >= 4 && command.startsWith("done")) {
            if (command.length() == 4)  {
                throw new DukeException("The Done command must be followed by a number!");
            } else {
                if (isNumeric(command.substring(5))) {
                    return true;
                } else {
                    throw new DukeException("The Done command requires a number, separated by whitespace!");
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
     * @throws DukeException Either a syntax error or lack of description.
     * @return returns true if it is said command.
     */
    private boolean isToDo(String command) throws DukeException {
        if (command.length() >= 4 && command.startsWith("todo")) {
            if (command.length() == 4) {
                throw new DukeException("ToDo", 1);
            } else if (command.charAt(4) != ' ') {
                throw new DukeException("ToDo", 0);
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
     * @throws DukeException Either a syntax error or lack of description.
     * @return returns true if it is said command.
     */
    private boolean isDeadLine(String command) throws DukeException{
        if (command.length() >= 8 && command.startsWith("deadline")) {
            if (command.length() == 8) {
                throw new DukeException("Deadline", 1);
            } else if (command.charAt(8) != ' ') {
                throw new DukeException("Deadline", 0);
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
     * @throws DukeException Either a syntax error or lack of description.
     * @return returns true if it is said command.
     */
    private boolean isEvent(String command) throws DukeException {
        if (command.length() >= 5 && command.startsWith("event")) {
            if (command.length() == 5) {
                throw new DukeException("Event", 1);
            } else if (command.charAt(5) != ' '){
                throw new DukeException("Event", 0);
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
     * @throws DukeException Syntax errors, spacing.
     * @return returns true if it is said command.
     */
    private boolean isDelete(String command) throws DukeException {
        if (command.length() >= 6 && command.startsWith("delete")) {
            if (command.length() == 6)  {
                throw new DukeException("The Delete command must be followed by a number!");
            } else {
                if (command.charAt(6) != ' ') {
                    throw new DukeException("The Delete command requires a number, separated by whitespace!");
                } else if (isNumeric(command.substring(7))) {
                    taskNumber = Integer.parseInt(command.substring(7));
                    return true;
                } else {
                    throw new DukeException("The Delete command requires a number, separated by whitespace!");
                }
            }
        } else {
            return false;
        }
    }

    private boolean isFind(String command) throws DukeException {
        int commandLength = command.length();
        if (commandLength >= 4 && command.startsWith("find")) {
            if (commandLength == 4) {
                throw new DukeException("The Find command must be followed by a string!");
            } else if (command.charAt(4) != ' ') {
                throw new DukeException("The Find command requires a string, separated by whitespace!");
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
        } catch(NumberFormatException e){
            return false;
        }
    }
}
