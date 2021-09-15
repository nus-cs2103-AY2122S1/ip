package duke.data;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.ContactCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.information.Contact;
import duke.information.Deadline;
import duke.information.Event;
import duke.information.ToDo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class that parses a user's input.
 */
public class Parser {
    /** The different formats of date/time allowed as input */
    private final static String DATE_AND_TIME_FORMAT[] = {
        "M/y", "d/M/y", "d-M-y", "M/y HHmm", "d/M/y HHmm", "d-M-y HHmm"
    };

    //@@author addressbook-level2-reused
    //Reused from https://github.com/se-edu/addressbook-level2
    // with minor modifications
    /** Format of a comment input line. Comment lines are silently consumed when reading user input. */
    private static final String COMMENT_LINE_FORMAT_REGEX = "#.*";

    /**
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is parsed as a comment, is only whitespace, or is empty.
     *
     * @param rawInputLine full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    private static boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty() || isCommentLine(rawInputLine);
    }

    /**
     * Returns true if the user input line is a comment line.
     *
     * @param rawInputLine full raw user input line.
     * @return true if input line is a comment.
     */
    private static boolean isCommentLine(String rawInputLine) {
        return rawInputLine.trim().matches(COMMENT_LINE_FORMAT_REGEX);
    }
    //@@author

    /**
     * Checks the keyword of the input and return the corresponding command.
     *
     * @param userInput The text that the user inputs.
     * @return A Command that does what the keyword intended when executed.
     */
    public static Command parse(String userInput) {
        if (shouldIgnore(userInput)) {
            throw new DukeException("Invalid Keyword.");
        }

        String[] userInputArray = userInput.split(" ");
        String keyword = userInputArray[0];
        assert keyword != null : "Keyword should not be null";

        switch (keyword) {

        case "deadline":
            return prepareAddCommand("deadline ", userInput, userInputArray, "/by");

        case "event":
            return prepareAddCommand("event ", userInput, userInputArray, "/at");

        case "todo":
            return prepareAddCommand("todo ", userInput, userInputArray, "");

        case "done":
            return prepareDoneCommand(userInputArray);

        case "list":
            return prepareListCommand(userInputArray);

        case "delete":
            return prepareDeleteCommand(userInputArray);

        case "bye":
            return prepareExitCommand(userInputArray);

        case "find":
            return prepareFindCommand(userInputArray);

        case "contact":
            return prepareContactCommand( userInput, "/about");

        case "help":
            return prepareHelpCommand(userInputArray);

        default:
            throw new DukeException("Invalid Keyword.");
        }
    }

    /**
     * Checks for invalid inputs and returns an add command if input is valid.
     *
     * @param keyword The type of task.
     * @param userInput userInput from parse method.
     * @param userInputArray userInputArray from parse method.
     * @param specialPhrase The string to split the task description(if any).
     * @return An AddCommand.
     */
    private static Command prepareAddCommand(String keyword,
            String userInput, String[] userInputArray, String specialPhrase) {
        String userInputWithoutKeyword = userInput.replace(keyword, "");
        switch (keyword) {
        case "todo ":
            //checks if description is empty
            if (userInputArray.length > 1) {
                ToDo newToDo = new ToDo(userInputWithoutKeyword);
                return new AddCommand(newToDo);
            } else {
                throw new DukeException("The description of a todo cannot be empty.");
            }

        case "deadline ":
            //Checks if there is a description
            if (userInputArray.length == 1 || userInput.endsWith(specialPhrase)) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            userInputWithoutKeyword = userInput.replace(keyword, "");
            String[] updatedDeadline = userInputWithoutKeyword.split(" " + specialPhrase + " ");
            //Returns error if user enters less/more than one special phrase
            if (updatedDeadline.length != 2) {
                throw new DukeException("I'm sorry, please have ONE " + specialPhrase + " in your description!");
            }
            String deadlineDescription = updatedDeadline[0];
            String deadlineDetail = updatedDeadline[1];
            return new AddCommand(prepareDeadline(deadlineDescription, deadlineDetail));

        //Default will be for "event" keyword
        default:
            //Checks if there is a description
            if (userInputArray.length == 1 || userInput.endsWith(specialPhrase)) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            userInputWithoutKeyword = userInput.replace(keyword, "");
            String[] updatedEvent = userInputWithoutKeyword.split(" " + specialPhrase + " ");
            //Returns error if user enters less/more than one special phrase
            if (updatedEvent.length != 2) {
                throw new DukeException("I'm sorry, please have ONE " + specialPhrase + " in your description!");
            } else {
                String eventDescription = updatedEvent[0];
                String eventDetail = updatedEvent[1];
                Event newEvent = new Event(eventDescription, eventDetail);
                return new AddCommand(newEvent);
            }
        }
    }

    /**
     * Checks if the date and time inputted by the user follows any of the formats allowed.
     * To be used only in prepareAddCommand method.
     *
     * @param description Deadline description.
     * @param dateTime The date and time input as a string.
     * @return A deadline if input is valid, else throws a DukeException.
     */
    private static Deadline prepareDeadline(String description, String dateTime) {
        for (String dateTimeFormat : DATE_AND_TIME_FORMAT) {
            try {
                if (dateTimeFormat.equals("M/y")) {
                    YearMonth userInput = YearMonth.parse(dateTime,
                            DateTimeFormatter.ofPattern(dateTimeFormat));
                    if (userInput.getYear() < 2000) {
                        userInput = userInput.plusYears(2000);
                    }
                    return new Deadline(description, userInput);
                }
                if (dateTimeFormat.split(" ").length > 1) {
                    LocalDateTime userInput = LocalDateTime.parse(dateTime,
                            DateTimeFormatter.ofPattern(dateTimeFormat));
                    if (userInput.getYear() < 2000) {
                        userInput = userInput.plusYears(2000);
                    }
                    return new Deadline(description, userInput);
                } else {
                    LocalDate userInput = LocalDate.parse(dateTime,
                            DateTimeFormatter.ofPattern(dateTimeFormat));
                    if (userInput.getYear() < 2000) {
                        userInput = userInput.plusYears(2000);
                    }
                    return new Deadline(description, userInput);
                }
            } catch (DateTimeParseException e) {
                //Have to loop through all the different date and time formats to check if the user input
                //is of a valid format, DateTimeParseException will be thrown whenever the format doesn't fit.
                //If an input does not fit any format, a DukeException will be thrown after the loop has finished.
            }
        }
        throw new DukeException("Please enter a date of the following format:\n " +
                "\"Month/Year\"\n \"Day/Month/Year\"\n \"Day-Month-Year\"\n" +
                " \"Month/Year HourMinutes\"\n \"Day/Month/Year HourMinute\"\n \"Day-Month-Year HourMinute\"\n");
    }

    /**
     * Checks for invalid inputs and returns a list command if input is valid.
     *
     * @param userInputArray userInputArray from parse method.
     * @return A ListCommand.
     */
    private static Command prepareListCommand(String[] userInputArray) {
        if (userInputArray.length == 1) {
            return new ListCommand();
        } else {
            throw new DukeException("The description of a list MUST be empty.");
        }
    }

    /**
     * Checks for invalid inputs and returns a done command if input is valid.
     *
     * @param userInputArray userInputArray from parse method.
     * @return A DoneCommand.
     */
    private static Command prepareDoneCommand(String[] userInputArray) {
        //checks if there is a 2nd input(completed task number)
        if (userInputArray.length == 2) {
            try {
                int taskNumber = Integer.parseInt(userInputArray[1]);
                return new DoneCommand(taskNumber - 1);
            } catch (NumberFormatException e) {
                throw new DukeException("I'm sorry, please input a number instead!");
            }
        } else {
            throw new DukeException("I'm sorry, please input the number of the completed task");
        }
    }

    /**
     * Checks for invalid inputs and returns a delete command if input is valid.
     *
     * @param userInputArray userInputArray from parse method.
     * @return A DeleteCommand.
     */
    private static Command prepareDeleteCommand(String[] userInputArray) {
        if (userInputArray.length == 3) {
            try {
                int taskNumber = Integer.parseInt(userInputArray[2]);
                switch (userInputArray[1]) {
                case "t" :
                    return new DeleteCommand(taskNumber - 1, 0);

                case "c" :
                    return new DeleteCommand(taskNumber - 1, 1);

                default:
                    throw new DukeException("I'm sorry, please specify which list to delete from!");
                }
            } catch (NumberFormatException e) {
                throw new DukeException("I'm sorry, please input a number instead!");
            }
        } else {
            throw new DukeException("Please follow this format: delete CONTACT/TASK INDEX.");
        }
    }

    /**
     * Checks for invalid inputs and returns an exit command if input is valid.
     *
     * @param userInputArray userInputArray from parse method.
     * @return An ExitCommand.
     */
    private static Command prepareExitCommand(String[] userInputArray) {
        //Checks if description is left empty
        if (userInputArray.length == 1) {
            return new ExitCommand();
        } else {
            throw new DukeException("The description of a bye MUST be empty.");
        }
    }

    /**
     * Checks for invalid inputs and returns a find command if input is valid.
     *
     * @param userInputArray userInputArray from parse method.
     * @return a FindCommand.
     */
    private static Command prepareFindCommand(String[] userInputArray) {
        //checks if there is a 2nd input(word to be searched)
        if (userInputArray.length == 2) {
            return new FindCommand(userInputArray[1]);
        } else {
            throw new DukeException("Please enter the word to search for");
        }
    }

    /**
     * Checks for invalid inputs and returns a contact command if input is valid.
     *
     * @param userInput userInput from parse method.
     * @param specialPhrase The string to split the task description(if any).
     * @return A ContactCommand.
     */
    private static Command prepareContactCommand (String userInput, String specialPhrase) {
        String userInputWithoutKeyword = userInput.replace("contact ", "");
        //For contacts without details
        if(!userInputWithoutKeyword.contains(specialPhrase)) {
            return new ContactCommand(new Contact(userInputWithoutKeyword, " "));
        }
        String[] updatedContact = userInputWithoutKeyword.split(" " + specialPhrase + " ");
        //Returns error if user enters less/more than one special phrase
        if (updatedContact.length != 2) {
            throw new DukeException("I'm sorry, please have ONE " + specialPhrase + " in your description!");
        }
        String contactName = updatedContact[0];
        String contactDetail = updatedContact[1];
        return new ContactCommand(new Contact(contactName, contactDetail));
    }

    /**
     * Checks for invalid inputs and returns a help command if input is valid.
     *
     * @param userInputArray userInputArray from parse method.
     * @return A HelpCommand.
     */
    private static Command prepareHelpCommand(String[] userInputArray) {
        if (userInputArray.length == 1) {
            return new HelpCommand();
        } else {
            throw new DukeException("The description of a help MUST be empty.");
        }
    }
}
