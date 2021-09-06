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
import duke.command.ListCommand;
import duke.command.ToDoCommand;

/**
 * Parses user input.
 */
public class Parser {

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
        } else if (input.matches("done (.*)")) {
            try {
                String text = input.substring(5);
                int taskNo = Integer.parseInt(text);
                return new DoneCommand(taskNo);
            } catch (NumberFormatException e) {
                String str = "Please use the format done <task No.>";
                throw new DukeException(str);
            }
        } else if (input.matches("delete (.*)")) {
            try {
                String text = input.substring(7);
                int taskNo = Integer.parseInt(text);
                return new DeleteCommand(taskNo);
            } catch (NumberFormatException e) {
                String str = "Please use the format delete <task No.>";
                throw new DukeException(str);
            }
        } else if (input.matches("todo(.*)")) {
            try {
                int start = input.indexOf("todo ");
                String validInput = input.substring(start);
                String taskDesc = validInput.trim().substring(5);
                return new ToDoCommand(taskDesc);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("The todo task description cannot be empty. "
                        + "Please use format todo <desc>");
            }
        } else if (input.matches("deadline(.*)")) {
            try {
                int start = input.indexOf("deadline ");
                String validInput = input.substring(start);
                String taskDesc = validInput.substring(9);
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
        } else if (input.matches("event(.*)")) {
            try {
                int start = input.indexOf("event ");
                String validInput = input.substring(start);
                String taskDesc = validInput.substring(6);
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
        } else if (input.matches("find(.*)")) {
            try {
                //check if input is in the correct format
                int start = input.indexOf("find ");
                String validInput = input.substring(start);

                String keyword = validInput.trim().substring(5);
                return new FindCommand(keyword);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please use the format find <keyword>");
            }
        } else {
            throw new DukeException("I'm sorry, but I don't understand what that means :(");
        }
    }

}
