package core;

import commands.*;
import helpful_functions.HelpfulFunctions;

public class Parser {
    public static Command parse(String input, TaskList taskList) {
        try {
            checkValiditiy(input, taskList);
            String[] splittedInput = input.split(" ");
            switch (splittedInput[0]) {
                case "list":
                    return new ListCommand();
                case "done":
                    return new DoneCommand(Integer.valueOf(splittedInput[1]));
                case "delete":
                    return new DeleteCommand(Integer.valueOf(splittedInput[1]));
                case "bye":
                    return new ByeCommand();
                case "todo":
                    return Parser.validTodoHandler(input);
                case "deadline":
                    return Parser.validDeadlineHandler(input);
                case "event":
                    return Parser.validEventHandler(input);
                default:
                    // This is for any other erroneous input we did not catch from checkValidity
                    DukeException e = new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    return new ExceptionalCommand(e);
            }
        } catch (DukeException e) {
            return new ExceptionalCommand(e);
        }

    }

    public static boolean checkValiditiy(String input, TaskList taskList) throws DukeException {
        String[] splittedInput = input.split(" ");
        if (input.length() == 0) {
            throw new DukeException("☹ OOPS!!! The input cannot be empty.");
        }
        switch (splittedInput[0]) {
            case "todo":
                checkValidityTodo(input);
                break;
            case "deadline":
                checkValidityDeadline(input);
                break;
            case "event":
                checkValidityEvent(input);
                break;
            case "done":
                checkValidityDone(input, taskList);
                break;
            case "delete":
                checkValidityDelete(input, taskList);
                break;
        }
        return true;
    }

    public static void checkValidityTodo(String input) throws DukeException {
        String[] splittedInput = input.split(" ");
        if (splittedInput.length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    public static void checkValidityDeadline(String input) throws DukeException {
        String[] splittedInput = input.split(" ");
        if (splittedInput.length == 1) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
    }

    public static void checkValidityEvent(String input) throws DukeException {
        String[] splittedInput = input.split(" ");
        if (splittedInput.length == 1) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
    }

    public static void checkValidityDone(String input, TaskList taskList) throws DukeException {
        String[] splittedInput = input.split(" ");
        if (splittedInput.length == 1) {
            throw new DukeException("☹ OOPS!!! You must specify an index");
        } else {
            if (HelpfulFunctions.isInteger(splittedInput[1])) {
                int index = Integer.parseInt(splittedInput[1]);
                if (index < 1 || index > taskList.getSize()) {
                    throw new DukeException("☹ OOPS!!! Your index is out of range");
                }
            } else {
                throw new DukeException("☹ OOPS!!! Your second argument must be an integer");
            }
        }
    }

    public static void checkValidityDelete(String input, TaskList taskList) throws DukeException {
        String[] splittedInput = input.split(" ");
        if (splittedInput.length == 1) {
            throw new DukeException("☹ OOPS!!! You must specify an index");
        } else {
            if (HelpfulFunctions.isInteger(splittedInput[1])) {
                int index = Integer.parseInt(splittedInput[1]);
                if (index < 1 || index > taskList.getSize()) {
                    throw new DukeException("☹ OOPS!!! Your index is out of range");
                }
            } else {
                throw new DukeException("☹ OOPS!!! Your second argument must be an integer");
            }
        }
    }

    public static Command validTodoHandler(String input) {
        String[] splittedInput = input.split(" ", 2);
        return new TodoCommand(splittedInput[1]);
    }

    public static Command validDeadlineHandler(String input) {
        String details = input.split(" ", 2)[1];
        String regexForDetails = "/by";
        String splittedDetails[] = details.split(regexForDetails);
        return new DeadlineCommand(splittedDetails[0].trim(), splittedDetails[1].trim());
    }

    public static Command validEventHandler(String input) {
        String details = input.split(" ", 2)[1];
        String regexForDetails = "/at";
        String splittedDetails[] = details.split(regexForDetails);
        return new EventCommand(splittedDetails[0].trim(), splittedDetails[1].trim());
    }

}
