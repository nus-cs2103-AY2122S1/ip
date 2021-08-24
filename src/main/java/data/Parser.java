package data;

import command.*;
import task.Deadline;
import task.Event;
import task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Class that parses a user's input.
 */
public class Parser {

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
        //Checks if task is a todo
        if (specialPhrase.isEmpty()) {
            //checks if description is empty
            if (userInputArray.length > 1) {
                ToDo newToDo = new ToDo(userInput.replace(keyword, "").replaceFirst(" ", ""));
                return new AddCommand(newToDo);
            } else {
                throw new DukeException("The description of a todo cannot be empty.");
            }
        } else {
            //Checks if there is a description
            if (userInputArray.length == 1 || userInput.endsWith(specialPhrase)) {
                throw new DukeException("The description of a deadline cannot be empty.");
            } else {
                //Removes the keyword and splits the description using specialPhrase
                String[] updatedTask = userInput.replace(keyword + " ", "").split(" " + specialPhrase +" ");
                //Returns error if user enters less/more than one special phrase
                if (updatedTask.length != 2) {
                    throw new DukeException("I'm sorry, please have ONE "+ specialPhrase +" in your description!");
                } else {
                    String taskDescription = updatedTask[0];
                    String taskDetail = updatedTask[1];
                    if (keyword.equals("deadline")) {
                        try {
                            LocalDate.parse(taskDetail);
                        } catch (DateTimeParseException e) {
                            throw new DukeException("Please enter a valid date!");
                        }
                        Deadline newDeadline = new Deadline(taskDescription, LocalDate.parse(taskDetail));
                        return new AddCommand(newDeadline);
                    } else {
                        Event newEvent = new Event(taskDescription, taskDetail);
                        return new AddCommand(newEvent);
                    }
                }
            }
        }
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
}
