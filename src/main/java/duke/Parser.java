package duke;

/**
 * Represents the user command given to Duke.
 */
public class Parser {
    private String command;
    private String[] twoPart;
    private String[] time;

    /**
     * Class constructor that constructs a Parser object. It also stores a String[] that splits the command into 2 parts
     * based on the first space (eg. " ").
     *
     * @param command The user command given.
     */
    public Parser(String command) {
        this.command = command;
        this.twoPart = this.command.split(" ", 2);
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
    public int secondPartInInt() throws DukeException {
        try {
            return Integer.valueOf(this.twoPart[1]);
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
    public String secondPart() throws DukeException {
        try {
            return this.twoPart[1];
        } catch (ArrayIndexOutOfBoundsException error) {
            throw new DukeException("do remember to add your description!");
        }
    }

    /**
     * Returns a String array which consists of 2 components for Deadline, split from the second part of the user command.
     *
     * @return String array which contains components for a Deadline object.
     */
    public String[] deadline() {
        return this.twoPart[1].split(" /by ", 2);
    }

    /**
     * Returns a String array which consists of 2 components for Event, split from the second part of the user command.
     *
     * @return String array which contains components for a Event object.
     */
    public String[] event() {
        return this.twoPart[1].split(" /at ", 2);
    }

    /**
     * Checks if the user command is bye.
     *
     * @return True if user command is bye.
     */
    public boolean isBye() {
        if (this.command.equals("bye")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the user command is list.
     *
     * @return True if user command is list.
     */
    public boolean isList() {
        if (this.command.equals("list")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the user command is done.
     *
     * @return True if user command is done.
     */
    public boolean isDone() {
        if (this.twoPart[0].equals("done")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the user command is todo.
     *
     * @return True if user command is todo.
     */
    public boolean isToDo() {
        if (this.twoPart[0].equals("todo")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the user command is deadline.
     *
     * @return True if user command is deadline.
     */
    public boolean isDeadline() {
        if (this.twoPart[0].equals("deadline")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the user command is event.
     *
     * @return True if user command is event.
     */
    public boolean isEvent() {
        if (this.twoPart[0].equals("event")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Checks if the user command is delete.
     *
     * @return True if user command is delete.
     */
    public boolean isDelete() {
        if (this.twoPart[0].equals("delete")) {
            return true;
        } else {
            return false;
        }
    }
}
