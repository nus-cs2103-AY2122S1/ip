package duke;

/**
 * Represents the user command given to Duke.
 */
public class Parser {
    private String command;
    private String[] twoPart;

    private static final int SIZE_OF_TWO_PART = 2;
    private static final int FIRST_WORD = 0;
    private static final int REST_OF_COMMAND = 1;

    private static final String DEADLINE_COMMAND = " /by ";
    private static final String EVENT_COMMAND = " /at ";

    /**
     * Class constructor that constructs a Parser object. It also stores a String[] that splits the command into 2 parts
     * based on the first space (eg. " ").
     *
     * @param command The user command given.
     */
    public Parser(String command) {
        this.command = command;
        this.twoPart = this.command.split(" ", SIZE_OF_TWO_PART);
    }

    /**
     * Returns the user command given.
     *
     * @return User command.
     */
    public String getCommand() {
        return this.command;
    }

    /**
     * Returns the second part of the user command as an int.
     *
     * @return Second part of user command as an int.
     * @throws DukeException If there is no second part or if the second part of the user command cannot be converted
     * into an int.
     */
    public int getSecondPartInInt() throws DukeException {
        try {
            return Integer.valueOf(this.twoPart[REST_OF_COMMAND]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("which specific task from the list? Give a number!");
        } catch (NumberFormatException e) {
            throw new DukeException("which specific task from the list? Give a number!");
        }
    }

    /**
     * Returns the second part of the user command.
     *
     * @return Second part of user command
     * @throws DukeException If there is no second part of user command.
     */
    public String getSecondPart() throws DukeException {
        try {
            return this.twoPart[REST_OF_COMMAND];
        } catch (ArrayIndexOutOfBoundsException error) {
            throw new DukeException("do remember to add your description!");
        }
    }

    /**
     * Returns a String array which consists of 2 components for Deadline, split from the second part of the user command.
     *
     * @return String array which contains components for a Deadline object.
     */
    public String[] splitSecondPartForDeadline() {
        return this.twoPart[REST_OF_COMMAND].split(DEADLINE_COMMAND, SIZE_OF_TWO_PART);
    }

    /**
     * Returns a String array which consists of 2 components for Event, split from the second part of the user command.
     *
     * @return String array which contains components for a Event object.
     */
    public String[] splitSecondPartForEvent() {
        return this.twoPart[REST_OF_COMMAND].split(EVENT_COMMAND, SIZE_OF_TWO_PART);
    }

    /**
     * Checks if the user command is list.
     *
     * @return True if user command is list.
     */
    public boolean isList() {
        return this.command.equals("list");
    }

    /**
     * Checks if the user command is done.
     *
     * @return True if user command is done.
     */
    public boolean isDone() {
        return this.twoPart[FIRST_WORD].equals("done");
    }

    /**
     * Checks if the user command is todo.
     *
     * @return True if user command is todo.
     */
    public boolean isToDo() {
        return this.twoPart[FIRST_WORD].equals("todo");
    }

    /**
     * Checks if the user command is deadline.
     *
     * @return True if user command is deadline.
     */
    public boolean isDeadline() {
        return this.twoPart[FIRST_WORD].equals("deadline");
    }

    /**
     * Checks if the user command is event.
     *
     * @return True if user command is event.
     */
    public boolean isEvent() {
        return this.twoPart[FIRST_WORD].equals("event");
    }

    /**
     * Checks if the user command is delete.
     *
     * @return True if user command is delete.
     */
    public boolean isDelete() {
        return this.twoPart[FIRST_WORD].equals("delete");
    }

    /**
     * Checks if the user command is find.
     *
     * @return True if user command is find.
     */
    public boolean isFind() {
        return this.twoPart[FIRST_WORD].equals("find");
    }
}
