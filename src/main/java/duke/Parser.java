package duke;

import duke.command.Command;
import duke.command.DeadlineAddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventAddCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.TodoAddCommand;


/**
 * An interpreter that evaluates the user input and produces a Command based on
 * what the user has requested for.
 */
public class Parser {
    /**
     * This method evaluates a string given to it by the program and produces a Command
     * that does whatever the user has requested for.
     *
     * @param str String that is inputted by the user when prompted and passed into this method.
     * @return A Command that makes the Ui, Storage and TaskList perform various actions.
     * @throws DukeException An error thrown due to user input error.
     */
    public static Command parse(String str) throws DukeException {
        String[] splitBySpace = str.split("\\s+");
        if (str.equals("bye")) {
            return new ExitCommand();
        } else if (str.equals("list") || str.equals("l")) {
            return new ListCommand();
        } else if (splitBySpace[0].equals("done")) {
            int doneNumber = handleDoneCommand(splitBySpace);
            return new DoneCommand(doneNumber);
        } else if (splitBySpace[0].equals("deadline") || splitBySpace[0].equals("d")) {
            String title = handleTitleChecking(str);
            String endTime = findTime(str, "D");
            return new DeadlineAddCommand(title, endTime);
        } else if (splitBySpace[0].equals("event") || splitBySpace[0].equals("e")) {
            String title = handleTitleChecking(str);
            String startTime = findTime(str, "E");
            return new EventAddCommand(title, startTime);
        } else if (splitBySpace[0].equals("todo") || splitBySpace[0].equals("t")) {
            String description = handleToDoAddCommand(str, splitBySpace);
            return new TodoAddCommand(description);
        } else if (splitBySpace[0].equals("delete") || splitBySpace[0].equals("del")) {
            int deleteNumber = handleDeleteCommand(splitBySpace);
            return new DeleteCommand(deleteNumber);
        } else if (splitBySpace[0].equals("help") || splitBySpace[0].equals("h")) {
            return new HelpCommand();
        } else if (splitBySpace[0].equals("find") || splitBySpace[0].equals("f")) {
            String keyword = handleFindCommand(str, splitBySpace);
            return new FindCommand(keyword);
        }
        throw new DukeException("I'm sorry :( I don't quite seem to understand, try again pls!");
    }

    /**
     * Finds the string that contains the information of the time of event or deadline depending on the type.
     *
     * @param str The String containing the user input.
     * @param type The type of input they gave(e.g. "D" for deadline and "E" for event).
     * @return The string containing only the information of time.
     * @throws DukeException Thrown when there is a wrong user input.
     */
    private static String findTime(String str, String type) throws DukeException {
        try {
            String time;
            if (type.equals("E")) {
                time = str.split("/on ", 2)[1];
            } else {
                assert type.equals("D") : "Type error";
                time = str.split("/by ", 2)[1];
            }
            return time;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Sorry please indicate your deadline time with a '/by' after your "
                    + "deadline title or a '/on' after your event title");
        }
    }

    /**
     * Checks whether the title of the task given by the user is correct.
     *
     * @param str The String containing the user input, which contains both the title and the time.
     * @return The String with only the title.
     * @throws DukeException Thrown when titles are empty.
     */
    private static String handleTitleChecking(String str) throws DukeException {
        try {
            checkSlashFormatting(str);
            String title = str.split("/")[0].split("\\s+", 2)[1];
            checkDescriptionLength(title);
            return title;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("Oh no! Titles cannot be empty! Please try again");
        }
    }

    /**
     * Handles the steps taken before returning the FindCommand Command in parse.
     *
     * @param str The String containing the user input.
     * @param splitBySpace The String array that was divided by space.
     * @return A String containing just the keywords to find.
     * @throws DukeException Throws An exception when user input is invalid.
     */
    private static String handleFindCommand(String str, String[] splitBySpace) throws DukeException {
        checkTaskLength(splitBySpace);
        assert str.split("\\s+", 2).length == 2 : "No task specified to find";
        return str.split("\\s+", 2)[1];
    }

    /**
     * Handles the steps taken before DeleteCommand is called in parse.
     *
     * @param splitBySpace The String array split by whitespaces from the user input.
     * @return The task number the user wants to delete.
     * @throws DukeException Thrown when the user input is invalid.
     */
    private static int handleDeleteCommand(String[] splitBySpace) throws DukeException {
        checkTaskLength(splitBySpace);
        assert splitBySpace.length > 1 : "No task specified to delete";
        return Integer.parseInt(splitBySpace[1]);
    }

    /**
     * Handles the steps before a DoneCommand Command is called in parse.
     *
     * @param splitBySpace The String array split by whitespaces from the user input.
     * @return The task number the user wants to mark as done.
     * @throws DukeException Thrown when user input is invalid.
     */
    private static int handleDoneCommand(String[] splitBySpace) throws DukeException {
        checkTaskLength(splitBySpace);
        assert splitBySpace.length > 1 : "No task indicated.";
        return Integer.parseInt(splitBySpace[1]);
    }

    /**
     * Handles all steps to create a ToDoAddCommand, including checking for violations in user input.
     *
     * @param str The string input of the user.
     * @param splitBySpace The String array that was split by spaces in the original parse().
     * @return The String that contains the Description to be added to ToDoAddCommand.
     * @throws DukeException Thrown when there is a user input violation.
     */
    private static String handleToDoAddCommand(String str, String[] splitBySpace) throws DukeException {
        checkTaskLength(splitBySpace);
        assert splitBySpace.length > 1 : "No todo specified";
        String description = str.split("\\s+", 2)[1];
        checkDescriptionEmpty(description);
        return description;
    }

    /**
     * Checks whether the description is empty.
     *
     * @param description The description to be checked whether it is empty.
     * @throws DukeException Thrown when the description is empty.
     */
    private static void checkDescriptionEmpty(String description) throws DukeException {
        if (description.equals("") || description.equals(" ")) {
            throw new DukeException("Oh no! Titles cannot be empty! Please try again");
        }
    }

    /**
     * Checks whether the description length is too short.
     *
     * @param title The title of the item to be added.
     * @throws DukeException Thrown when titles are seen to be empty.
     */
    private static void checkDescriptionLength(String title) throws DukeException {
        if (title.length() <= 0) {
            throw new DukeException("Oh no! Titles cannot be empty! Please try again");
        }
    }

    /**
     * Checks for the presence of the '/' in the String.
     *
     * @param str The String that contains the user input.
     * @throws DukeException Thrown when there is no presence of the '/'.
     */
    private static void checkSlashFormatting(String str) throws DukeException {
        if (!str.contains("/")) {
            throw new DukeException("Sorry please indicate your deadline time with a '/by' after your "
                    + "deadline title or a '/on' after your event title");
        }
    }

    /**
     * Checks whether the task length is sufficient for certain commands such as delete, done and find.
     *
     * @param splitBySpace The String array that was split by spacings.
     * @throws DukeException Thrown when there is nothing for the command to do.
     */
    private static void checkTaskLength(String[] splitBySpace) throws DukeException {
        if (splitBySpace.length == 1) {
            throw new DukeException("Sorry please indicate a task number to update!");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return obj instanceof Parser;
    }
}
