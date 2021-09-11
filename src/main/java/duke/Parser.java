package duke;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.ListAllCommand;
import duke.commands.TodoCommand;

/**
 * Class that represents a Parser object for parsing Strings to Commands
 */
public class Parser {

    /**
     * Returns a Command by parsing a user provided String input
     * that can be executed.
     *
     * @param fullCommand A String input direct from the user.
     * @return A subtype of Command
     * @throws DukeException In the event of parse or invalid argument errors
     */
    public static Command parse(String fullCommand) throws DukeException {
        // Check if user input is empty
        if (fullCommand.equals("")) {
            throw new DukeException("Please enter a command!");
        }

        // Command by the user should at least have 1 word
        assert fullCommand.split(" ").length >= 1 : "Input should not be empty!";

        String firstToken = fullCommand.split(" ")[0];

        // Check if fullCommand is "bye"
        if (fullCommand.equals("bye")) {
            return new ByeCommand();

        // Check if fullCommand is "list"
        } else if (fullCommand.equals("list")) {
            return new ListAllCommand();

        // Check if fullCommand starts with "delete"
        } else if (firstToken.equals("delete")) {
            try {
                // Check that delete is used correctly
                if (fullCommand.split(" ").length <= 1) {
                    throw new DukeException("☹ OOPS!!! Please provide a valid task number.");
                }

                // Determine index of task to delete
                int index = Integer.parseInt(fullCommand.split(" ")[1]);
                return new DeleteCommand(index);

            } catch (NumberFormatException e) {
                throw new DukeException("☹ OOPS!!! Please provide a valid task number.");
            }

        // Check if fullCommand starts with "done"
        } else if (firstToken.equals("done")) {
            try {
                // Check that delete is used correctly
                if (fullCommand.split(" ").length <= 1) {
                    throw new DukeException("☹ OOPS!!! Please provide a valid task number.");
                }

                // Determine index of task to mark as done
                int index = Integer.parseInt(fullCommand.split(" ")[1]);

                return new DoneCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("☹ OOPS!!! Please provide a valid task number.");
            }

        // Check if user adding a ToDo
        } else if (firstToken.equals("todo")) {
            if (fullCommand.split(" ").length <= 1) {
                throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
            }
            String description = fullCommand.substring(5);

            // Substring extracted for description should not be empty
            assert !description.equals("") : "ToDo description should not be empty";

            return new TodoCommand(description);

        // Check if user adding a Deadline
        } else if (firstToken.equals("deadline")) {
            try {
                // Check for valid description provided
                if (fullCommand.split(" /by ")[0].split(" ").length <= 1) {
                    throw new DukeException("☹ OOPS!!! Please provide a valid deadline description.");
                }

                // Check for valid due date provided
                if (fullCommand.split(" /by ").length != 2) {
                    throw new DukeException("☹ OOPS!!! Please provide a valid due date.");
                }
                String rawDueDate = fullCommand.split("/by")[1].strip();

                // rawDueDate provided by user should not be an empty string
                assert !rawDueDate.equals("") : "Datetime provided for Deadline cannot be empty!";

                // Initialize datetime formatter
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                LocalDateTime dueDateTime = LocalDateTime.parse(rawDueDate, formatter);
                String description = fullCommand
                    .split("/by")[0]
                    .strip()
                    .substring(9);

                // Provided description should not be empty
                assert !description.equals("") : "Description provided for Deadline cannot be empty!";

                return new DeadlineCommand(description, dueDateTime);
            } catch (DateTimeParseException e) {
                throw new DukeException("☹ OOPS!!! Please provide a valid due date.");
            }

        // Check if user adding an Event
        } else if (firstToken.equals("event")) {
            try {
                // Check for valid description provided
                if (fullCommand.split(" /at ")[0].split(" ").length <= 1) {
                    throw new DukeException("☹ OOPS!!! Please provide a valid event description.");
                }

                // Check for valid event time provided
                if (fullCommand.split(" /at ").length != 2) {
                    throw new DukeException("☹ OOPS!!! Please provide a valid event time.");
                }
                String rawEventDateTime = fullCommand.split("/at")[1].strip();

                // rawEventDateTime should not be empty
                assert !rawEventDateTime.equals("") : "Datetime input for Event should not be empty!";

                // Initialize datetime formatter
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                LocalDateTime eventDateTime = LocalDateTime.parse(rawEventDateTime, formatter);
                String description = fullCommand
                    .split("/at")[0]
                    .strip()
                    .substring(6);

                // Description for Event should not be empty
                assert !description.equals("") : "Description for Event should not be empty";

                return new EventCommand(description, eventDateTime);
            } catch (DateTimeParseException e) {
                throw new DukeException("☹ OOPS!!! Please provide a valid event time.");
            }

        // Check if fullCommand starts with "find"
        } else if (firstToken.equals("find")) {
            // Validate command and arguments
            if (fullCommand.split(" ").length <= 1) {
                throw new DukeException("Please provide a valid search term!");
            }

            // Parse search terms
            String searchTerms = fullCommand.substring(5).strip();

            // Search terms should not be empty
            assert !searchTerms.equals("") : "Search terms for Find should not be empty!";

            return new FindCommand(searchTerms);

        // Otherwise, throw an error
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
