package data;

import command.*;
import task.Deadline;
import task.Event;
import task.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Parses user input.
 */
public class Parser {

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

    private static Command prepareAddCommand(String keyword, String userInput, String[] userInputArray, String specialPhrase) {
        //task.Task is a todo
        if (specialPhrase.isEmpty()) {
            //checks if description is empty
            if (userInputArray.length > 1) {
                ToDo newToDo = new ToDo(userInput.replace(keyword, "").replaceFirst(" ", ""));
                return new AddCommand(newToDo);
            } else {
                throw new DukeException("The description of a todo cannot be empty.");
            }
        } else {
            if (userInputArray.length == 1 || userInput.endsWith(specialPhrase)) {
                throw new DukeException("The description of a deadline cannot be empty.");
            } else {
                //checks if there is a "/by" to separate the description
                if (userInput.contains(specialPhrase)) {
                    //Removes the "deadline" string and splits the description using " /by "
                    String[] updatedDeadline = userInput.replace(keyword + " ", "").split(" " + specialPhrase +" ");
                    //Returns error if user enters more than one "/by"
                    if (updatedDeadline.length > 2) {
                        throw new DukeException("I'm sorry, please only have ONE '/by' in your description!");
                    } else {
                        String deadlineDescription = updatedDeadline[0];
                        String deadlineBy = updatedDeadline[1];
                        if (keyword.equals("deadline")) {
                            try {
                                LocalDate.parse(deadlineBy);
                            } catch (DateTimeParseException e) {
                                throw new DukeException("Please enter a valid date!");
                            }
                            Deadline newDeadline = new Deadline(deadlineDescription, LocalDate.parse(deadlineBy));
                            return new AddCommand(newDeadline);
                        } else {
                            Event newDeadline = new Event(deadlineDescription, deadlineBy);
                            return new AddCommand(newDeadline);
                        }
                    }
                } else {
                    throw new DukeException("I'm sorry, please add a '/by' in your description!");
                }
            }
        }
    }

    private static Command prepareListCommand(String[] userInputArray) {
        if (userInputArray.length == 1) {
            return new ListCommand();
        } else {
            throw new DukeException("The description of a list MUST be empty.");
        }
    }

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
