package duke.parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;



/**
 * Parses user input.
 */
public class Parser {

    public static final int DONE_OFFSET = 5;
    public static final int DELETE_OFFSET = 7;
    public static final int TODO_OFFSET = 5;
    public static final int DEADLINE_OFFSET = 9;
    public static final int EVENT_OFFSET = 6;
    public static final int FIND_OFFSET = 5;


    /**
     * Parses user input into command for execution.
     *
     * @param input full user input string
     * @return the command based on user input
     * @throws DukeException if user input string is not a valid format
     */
    public static Command getCommand(String input) throws DukeException {
        if (input.trim().equals("bye")) {
            return new ExitCommand();
        } else if (input.trim().equals("list")) {
            return new ListCommand();
        } else if (input.trim().equals("help")) {
            return new HelpCommand();
        } else if (input.matches("done (.*)")) {
            return parseDoneCommand(input);
        } else if (input.matches("delete (.*)")) {
            return parseDeleteCommand(input);
        } else if (input.matches("todo(.*)")) {
            return parseToDoCommand(input);
        } else if (input.matches("deadline(.*)")) {
            return parseDeadlineCommand(input);
        } else if (input.matches("event(.*)")) {
            return parseEventCommand(input);
        } else if (input.matches("find(.*)")) {
            return parseFindCommand(input);
        } else {
            throw new DukeException("I'm sorry, but I don't understand what that means :(\n"
                    + "To get started enter 'help'");
        }

    }

    /**
     * parses user input into a command to mark task as done.
     * @param input user input
     * @return the command to mark specified task as done
     * @throws DukeException if input format or task no is invalid
     */
    public static DoneCommand parseDoneCommand(String input) throws DukeException {
        try {
            String text = input.substring(DONE_OFFSET);
            int taskNo = Integer.parseInt(text);
            return new DoneCommand(taskNo);
        } catch (NumberFormatException e) {
            String str = "Please use the format done <task No.>";
            throw new DukeException(str);
        }
    }

    /**
     * parses user input into a command to delete a command.
     * @param input user input
     * @return the command to delete a specified task
     * @throws DukeException if input format or task no is invalid
     */
    public static DeleteCommand parseDeleteCommand(String input) throws DukeException {
        try {
            String text = input.substring(DELETE_OFFSET);
            int taskNo = Integer.parseInt(text);
            return new DeleteCommand(taskNo);
        } catch (NumberFormatException e) {
            String str = "Please use the format delete <task No.>";
            throw new DukeException(str);
        }
    }

    /**
     * parses user input into a command to add a new todo
     * @param input user input
     * @return the command to add a todo
     * @throws DukeException if user input format is invalid
     */
    public static ToDoCommand parseToDoCommand(String input) throws DukeException {
        try {
            int start = input.indexOf("todo ");
            String validInput = input.substring(start);
            String taskDesc = validInput.trim().substring(TODO_OFFSET);
            return new ToDoCommand(taskDesc);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("The todo task description cannot be empty. "
                    + "Please use format todo <desc>");
        }
    }

    /**
     * parses user input into a command to add a new deadline
     * @param input user input
     * @return the command to add a deadline
     * @throws DukeException if user input format is invalid
     */
    public static DeadlineCommand parseDeadlineCommand(String input) throws DukeException {
        try {
            int start = input.indexOf("deadline ");
            String validInput = input.substring(start);
            String taskDesc = validInput.substring(DEADLINE_OFFSET);
            String[] fields = taskDesc.split(" /by ", 2);
            LocalDate date = LocalDate.parse(fields[1]);
            return new DeadlineCommand(fields);
        } catch (DateTimeParseException e) {
            throw new DukeException("Please use format deadline <description> /by <yyyy-mm-dd>.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("The Deadline description cannot be empty. "
                    + "Please use format deadline <description> /by <time>.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please use format deadline <description> /by <time>.");
        }
    }

    /**
     * parses user input into a command to add a new event
     * @param input user input
     * @return the command to add an event
     * @throws DukeException if user input format is invalid
     */
    public static EventCommand parseEventCommand(String input) throws DukeException {
        try {
            int start = input.indexOf("event ");
            String validInput = input.substring(start);
            String taskDesc = validInput.substring(EVENT_OFFSET);
            String[] fields = taskDesc.split(" /at ", 2);

            //make sure not empty
            String time = fields[1];
            return new EventCommand(fields);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("The Event description and time cannot be empty. "
                    + "Please use format event <description> /at <time>.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Please use format event <description> /at <time>.");
        }
    }

    /**
     * parses user input into a command to search tasklist
     * @param input user input
     * @return the command search based on keyword
     * @throws DukeException if user input format is invalid
     */
    public static FindCommand parseFindCommand(String input) throws DukeException {
        try {
            //check if input is in the correct format
            int start = input.indexOf("find ");
            String validInput = input.substring(start);

            String keyword = validInput.trim().substring(FIND_OFFSET);
            return new FindCommand(keyword);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Please use the format find <keyword>");
        }
    }
}
