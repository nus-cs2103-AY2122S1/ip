package duke.utils;

import duke.commands.AddDeadline;
import duke.commands.AddEvent;
import duke.commands.AddTodo;
import duke.commands.Command;
import duke.commands.Delete;
import duke.commands.Done;
import duke.commands.Find;
import duke.commands.ListTasks;
import duke.exceptions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * The Parser class is responsible for handling the user input and to perform
 * the necessary operations on the TaskList.
 */
public class Parser {

    private enum CommandType {
        LIST,
        FIND,
        ADD_EVENT,
        ADD_DEADLINE,
        ADD_TODO,
        DELETE,
        DONE
    }

    /**
     * Returns a boolean on whether the date input has a valid format.
     *
     * @param dateInput The date input from the user.
     * @return Boolean value indicating if the date has a valid format.
     */
    public static boolean isValidDateFormat(String dateInput) {
        // Date format checking adapted from:
        // https://stackoverflow.com/questions/2149680/regex-date-format-validation-on-java
        if (!dateInput.matches("\\d{4}-\\d{2}-\\d{2}")){
            return false;
        }
        SimpleDateFormat validFormat = new SimpleDateFormat("yyyy-MM-dd");
        validFormat.setLenient(false);
        try {
            validFormat.parse(dateInput);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    /**
     * Extracts the arguments from the user input, and returns in a List object.
     *
     * @param command The command type specified by the user.
     * @param input The command input from the user.
     * @return A List of arguments corresponding to the command type.
     * @throws DukeException
     */
    public static List<String> getArgs(CommandType command, String input) throws DukeException {
        List<String> args = new ArrayList<>();
        if (command == CommandType.DONE || command == CommandType.DELETE) {
            String taskId = input.split(" ")[1];
            args.add(taskId);
        } else if (command == CommandType.ADD_TODO) {
            if (input.split(" ").length < 2) throw new EmptyTaskDescriptionException();
            String todoName = input.split(" ", 2)[1];
            args.add(todoName);
        } else if (command == CommandType.ADD_DEADLINE) {
            String deadline = input.split("/by", 2)[1].trim();
            if (!isValidDateFormat(deadline)) throw new InvalidDateException();
            String beforeTime = input.split("/by", 2)[0].trim();
            if (beforeTime.split(" ").length < 2) throw new EmptyTaskDescriptionException();
            String deadlineName = beforeTime.split(" ", 2)[1];
            args.add(deadlineName);
            args.add(deadline);
        } else if (command == CommandType.ADD_EVENT) {
            String time = input.split("/at", 2)[1].trim();
            if (!isValidDateFormat(time)) throw new InvalidDateException();
            String beforeTime = input.split("/at", 2)[0].trim();
            if (beforeTime.split(" ").length < 2) throw new EmptyTaskDescriptionException();
            String eventName = beforeTime.split(" ", 2)[1];
            args.add(eventName.trim());
            args.add(time.trim());
        } else if (command == CommandType.FIND) {
            if (input.split(" ").length < 2) throw new EmptyQueryException();
            String query = input.split(" ", 2)[1];
            args.add(query);
        }
        return args;
    }

    /**
     * Returns a Command instance based on Command type and list of arguments.
     *
     * @param command The Command type.
     * @param args The list of arguments for the specified command.
     * @return A Command instance with arguments.
     */
    private static Command createCommandFromArgs(CommandType command, List<String> args) {
        if (command == CommandType.DONE) {
            int index = Integer.valueOf(args.get(0));
            return new Done(index);
        } else if (command == CommandType.ADD_TODO) {
            String name = args.get(0);
            return new AddTodo(name);
        } else if (command == CommandType.ADD_DEADLINE) {
            String name = args.get(0);
            String deadline = args.get(1);
            return new AddDeadline(name, deadline);
        } else if (command == CommandType.ADD_EVENT) {
            String name = args.get(0);
            String time = args.get(1);
            return new AddEvent(name, time);
        } else if (command == CommandType.DELETE) {
            int index = Integer.valueOf(args.get(0));
            return new Delete(index);
        } else if (command == CommandType.FIND) {
            String query = args.get(0);
            return new Find(query);
        } else {
            assert false : "This command with args shouldn't exist";
            return null;
        }
    }

    /**
     * Returns a Command object based on the user's input
     *
     * @param input The input provided by the user.
     * @return A Command object corresponding to the action intended by the user.
     */
    private static Command createCommand(String input) throws DukeException {
        if (input.equals("list")) {
            return new ListTasks();
        } else if (input.matches("done\\s[0-9][0-9]?")) {
            List<String> args = getArgs(CommandType.DONE, input);
            return createCommandFromArgs(CommandType.DONE, args);
        } else if (input.matches("delete\\s[0-9][0-9]?")) {
            List<String> args = getArgs(CommandType.DELETE, input);
            return createCommandFromArgs(CommandType.DELETE, args);
        } else if (input.matches("todo(.*?)")) {
            List<String> args = getArgs(CommandType.ADD_TODO, input);
            return createCommandFromArgs(CommandType.ADD_TODO, args);
        } else if (input.matches("deadline(.*?)/by(.*?)")) {
            List<String> args = getArgs(CommandType.ADD_DEADLINE, input);
            return createCommandFromArgs(CommandType.ADD_DEADLINE, args);
        } else if (input.matches("event(.*?)/at(.*?)")) {
            List<String> args = getArgs(CommandType.ADD_EVENT, input);
            return createCommandFromArgs(CommandType.ADD_EVENT, args);
        } else if (input.matches("find\\s(.*?)")) {
            List<String> args = getArgs(CommandType.FIND, input);
            return createCommandFromArgs(CommandType.FIND, args);
        } else {
            throw new InvalidCommandException();
        }
    }

    /**
     * Reads the user input and performs the corresponding action to the TaskList.
     *
     * @param taskList A TaskList object storing tasks.
     * @param commandInput Input provided by the user.
     * @throws DukeException If the user input is an invalid command.
     */
    public static TaskList parseInput(TaskList taskList, String commandInput) throws DukeException {
        Command userCommand = createCommand(commandInput);
        return userCommand.execute(taskList);
    }
}
