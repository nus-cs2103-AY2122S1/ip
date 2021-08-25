package duke.functionality;

import duke.commands.*;
import duke.exceptions.DukeException;
import duke.tasks.Todo;
import duke.tasks.Deadline;
import duke.tasks.Event;

/**
 * Creates a parser that makes sense of user inputs.
 */
public class Parser {

    /**
     * Returns the parsed Command input by the user.
     *
     * @param userInput The unparsed input from the user.
     * @return Parsed Command.
     * @throws DukeException If illegal/missing arguments are found.
     */
    public static Command parseInput(String userInput) throws DukeException {
        //TODO need to catch DukeException and IndexOutOfBoundsException somewhere
        String[] inputSplit = userInput.split(" ", 2);
        String command = inputSplit[0];

        if (command.equals("bye")) {
            return new ExitCommand();

        } else if (command.equals("list")) {
            return new ListCommand();

        } else if (command.equals(Todo.taskTag())) {
            if (checkInputLength(inputSplit)) {
                throw DukeException.missingInput("todo");
            }
            String taskName = inputSplit[1];
            return new AddCommand(Todo.taskTag(), taskName, null);

        } else if (command.equals(Deadline.taskTag())) {
            if (checkInputLength(inputSplit)) {
                throw DukeException.missingInput("deadline");
            }
            String args = inputSplit[1];
            String[] argsSplit = args.split(" /by ", 2);
            if (checkInputLength(argsSplit)) {
                throw new DukeException("☹ OOPS!!! Please indicate when the deadline for the task is.");
            }
            timeFormatChecker(argsSplit[1]);
            return new AddCommand(Deadline.taskTag(), argsSplit[0], argsSplit[1]);

        } else if (command.equals(Event.taskTag())) {
            if (checkInputLength(inputSplit)) {
                throw DukeException.missingInput("event");
            }
            String args = inputSplit[1];
            String[] argsSplit = args.split(" /at ", 2);
            if (checkInputLength(argsSplit)) {
                throw new DukeException("☹ OOPS!!! Please indicate the start and end time of the event.");
            }
            timeFormatChecker(argsSplit[1]);
            return new AddCommand(Event.taskTag(), argsSplit[0], argsSplit[1]);

        } else if (command.equals("done")) {
            if (checkInputLength(inputSplit)) {
                throw new DukeException("☹ OOPS!!! Please indicate which task you want to delete.");
            }
            String strTaskNum = inputSplit[1].split(" ")[0];
            int taskNum = Integer.parseInt(strTaskNum) - 1;
            return new DoneCommand(taskNum);
        } else if (command.equals("delete")) {
            if (checkInputLength(inputSplit)) {
                throw new DukeException("☹ OOPS!!! Please indicate which task you want to delete.");
            }
            String strTaskNum = inputSplit[1].split(" ")[0];
            int taskNum = Integer.parseInt(strTaskNum) - 1;
            return new DeleteCommand(taskNum);
        } else if (command.equals("find")) {
            if (checkInputLength(inputSplit)) {
                throw DukeException.missingInput("find");
            }
            String args = inputSplit[1];
            String keyword = args.contains(" ") ? args.split(" ", 2)[0] : args;
            return new FindCommand(keyword);
        } else {
            return new UnknownCommand();
        }
    }

    private static boolean checkInputLength(String[] inputSplit) {
        return inputSplit.length < 2;
    }

    private static void timeFormatChecker(String inputString) throws DukeException {
        if (!inputString.matches("\\d{4}-\\d{2}-\\d{2}")) {
            throw new DukeException("☹ OOPS!!! Please enter a valid format for the date/time of your task.");
        }
    }
}
