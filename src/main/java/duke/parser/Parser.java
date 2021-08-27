package duke.parser;

import duke.DukeException;
import duke.command.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
        if (input.trim().equals("bye")){
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
                String taskDesc = input.trim().substring(5);
                return new ToDoCommand(taskDesc);
            } catch(StringIndexOutOfBoundsException e) {
                throw new DukeException("The todo task description cannot be empty. "
                        + "Please use format todo <desc>");
            }
        } else if (input.matches("deadline(.*)")) {
            try {
                String taskDesc = input.substring(9);
                String[] fields = taskDesc.split(" /by ", 2);
                LocalDate date = LocalDate.parse(fields[1]);
                return new DeadlineCommand(fields);
            } catch (DateTimeParseException e) {
                throw new DukeException("Please use format deadline <description> /by <yyyy-mm-dd>.");
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("The duke.task.Deadline description cannot be empty. "
                        + "Please use format deadline <description> /by <time>.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please use format deadline <description> /by <time>.");
            }
        } else if (input.matches("event(.*)")) {
            try {
                String taskDesc = input.substring(6);
                String[] fields = taskDesc.split(" /at ", 2);
                //make sure not empty
                String time = fields[1];
                return new EventCommand(fields);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("The duke.task.Event description and time cannot be empty. "
                        + "Please use format event <description> /at <time>.");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please use format event <description> /at <time>.");
            }
        } else if (input.matches("find(.*)")) {
            try {
                String keyword = input.trim().substring(5);
                return new FindCommand(keyword);
            } catch(StringIndexOutOfBoundsException e) {
                throw new DukeException("Please use the format find <keyword>");
            }
        } else {
            throw new DukeException("I'm sorry, but I don't understand what that means :(");
        }
    }

}
