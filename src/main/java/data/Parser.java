package data;

import command.*;
import task.Deadline;
import task.Event;
import task.ToDo;

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

    /**
     * Checks the keyword of the input and return the corresponding command.
     *
     * @param userInput The text that the user inputs
     * @return A Command that does what the keyword intended when executed
     */
    public static Command parse(String userInput) {
        String[] userInputArray = userInput.split(" ");
        String keyword = userInputArray[0];

        switch (keyword) {

        case "deadline":
            return prepareAddCommand("deadline", userInput, userInputArray, "/by");

        case "event":
            return prepareAddCommand("event", userInput, userInputArray, "/at");

        case "todo":
            return prepareAddCommand("todo", userInput, userInputArray, "");

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

        default:
            throw new DukeException("Invalid Keyword.");
        }
    }

    /**
     * Checks for invalid inputs and returns an add command if input is valid.
     *
     * @param keyword The type of task
     * @param userInput userInput from parse method
     * @param userInputArray userInputArray from parse method
     * @param specialPhrase The string to split the task description(if any)
     * @return An AddCommand
     */
    private static Command prepareAddCommand(String keyword, String userInput, String[] userInputArray, String specialPhrase) {
        switch (keyword) {
        case "todo":
            //checks if description is empty
            if (userInputArray.length > 1) {
                ToDo newToDo = new ToDo(userInput.replace(keyword, "").replaceFirst(" ", ""));
                return new AddCommand(newToDo);
            } else {
                throw new DukeException("The description of a todo cannot be empty.");
            }

        case "deadline":
            //Checks if there is a description
            if (userInputArray.length == 1 || userInput.endsWith(specialPhrase)) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            //Removes the keyword and splits the description using specialPhrase
            String[] updatedDeadline = userInput.replace(keyword + " ", "").split(" " + specialPhrase + " ");
            //Returns error if user enters less/more than one special phrase
            if (updatedDeadline.length != 2) {
                throw new DukeException("I'm sorry, please have ONE " + specialPhrase + " in your description!");
            }
            String deadlineDescription = updatedDeadline[0];
            String deadlineDetail = updatedDeadline[1];
            return new AddCommand(prepareDeadline(deadlineDescription, deadlineDetail));


        case "event":
            //Checks if there is a description
            if (userInputArray.length == 1 || userInput.endsWith(specialPhrase)) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            //Removes the keyword and splits the description using specialPhrase
            String[] updatedEvent = userInput.replace(keyword + " ", "").split(" " + specialPhrase + " ");
            //Returns error if user enters less/more than one special phrase
            if (updatedEvent.length != 2) {
                throw new DukeException("I'm sorry, please have ONE " + specialPhrase + " in your description!");
            } else {
                String eventDescription = updatedEvent[0];
                String eventDetail = updatedEvent[1];
                Event newEvent = new Event(eventDescription, eventDetail);
                return new AddCommand(newEvent);
            }

        default:
            throw new DukeException("This error should never occur!");
        }
    }

    /**
     * Checks if the date and time inputted by the user follows any of the formats allowed.
     * To be used only in prepareAddCommand method.
     *
     * @param description Deadline description
     * @param dateTime The date and time input as a string
     * @return A deadline if input is valid, else throws a DukeException
     */
    private static Deadline prepareDeadline(String description, String dateTime) {
        for (String dateTimeFormat : DATE_AND_TIME_FORMAT) {
            try {
                if (dateTimeFormat == "M/y") {
                    YearMonth userInput = YearMonth.parse(dateTime, DateTimeFormatter.ofPattern(dateTimeFormat));
                    if (userInput.getYear() < 2000) {
                        userInput = userInput.plusYears(2000);
                    }
                    return new Deadline(description, userInput);
                }
                if (dateTimeFormat.split(" ").length > 1) {
                    LocalDateTime userInput = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(dateTimeFormat));
                    if (userInput.getYear() < 2000) {
                        userInput = userInput.plusYears(2000);
                    }
                    return new Deadline(description, userInput);
                } else {
                    LocalDate userInput = LocalDate.parse(dateTime, DateTimeFormatter.ofPattern(dateTimeFormat));
                    if (userInput.getYear() < 2000) {
                        userInput = userInput.plusYears(2000);
                    }
                    return new Deadline(description, userInput);
                }
            } catch (DateTimeParseException e) {
            }
        }
        throw new DukeException("Please enter a valid date!");
    }

    /**
     * Checks for invalid inputs and returns a list command if input is valid.
     *
     * @param userInputArray userInputArray from parse method
     * @return A ListCommand
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
     * @param userInputArray userInputArray from parse method
     * @return A DoneCommand
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
     * @param userInputArray userInputArray from parse method
     * @return A DeleteCommand
     */
    private static Command prepareDeleteCommand(String[] userInputArray) {
        //checks if there is a 2nd input(task number to be deleted)
        if (userInputArray.length == 2) {
            try {
                int taskNumber = Integer.parseInt(userInputArray[1]);
                return new DeleteCommand(taskNumber - 1);
            } catch (NumberFormatException e) {
                throw new DukeException("I'm sorry, please input a number instead!");
            }
        } else {
            throw new DukeException("I'm sorry, please input the number of the task you wish to delete.");
        }
    }

    /**
     * Checks for invalid inputs and returns an exit command if input is valid.
     *
     * @param userInputArray userInputArray from parse method
     * @return An ExitCommand
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
     * @param userInputArray userInputArray from parse method
     * @return a FindCommand
     */
    private static Command prepareFindCommand(String[] userInputArray) {
        //checks if there is a 2nd input(word to be searched)
        if (userInputArray.length == 2) {
            return new FindCommand(userInputArray[1]);
        } else {
            throw new DukeException("Please enter the word to search for");
        }
    }
}
