package duke.parser;

import duke.command.*;
import duke.exception.*;
import duke.task.*;

import java.util.regex.Pattern;

/**
 * A class that will make sense of the user's commands through string parsing
 */
public class Parser {
    private static final Pattern PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");

    /**
     * The main method to decide which parsing is needed for the user input
     * @param input the input keyed in by user
     * @return the type of command the input is
     * @throws DukeException if the users enter a wrong input
     */
    public static Command parse(String input) throws DukeException{
        if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.startsWith("done")) {
            int index = parseDone(input);
            return new DoneCommand(index);
        } else if (input.startsWith("delete")) {
            int index = parseDelete(input);
            return new DeleteCommand(index);
        } else {
            if (!input.startsWith("todo") && !input.startsWith("deadline") && !input.startsWith("event")) {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-( \n");
            }
            Task task;
            if (input.startsWith("todo")) {
                task = parseTodo(input);
            } else if (input.startsWith("deadline")) {
                task = parseDeadline(input);
            } else {
                task = parseEvent(input);
            }
            return new AddCommand(task);
        }
    }

    /**
     * A method that parses users inputs that wishes to mark a task as done
     * @param input the user's input
     * @return the index of the task user wishes to mark as done
     * @throws DukeException if the user's input is in an wrong format
     */
    public static int parseDone(String input) throws DukeException {
        String numberString = String.valueOf(input.charAt(5));
        if (!PATTERN.matcher(numberString).matches()) {
            throw new DukeException("Please enter \'done [index of item]\' to mark item as done.\n");
        }
        int number = Integer.parseInt(String.valueOf(numberString));
        if (number > TaskList.getSize() || number < 0) {
            throw new DukeException("Item does not exist, we cannot mark it as done.\n");
        }
        Task currTask = TaskList.get(number - 1);
        if (currTask.getStatusIcon().equals(String.valueOf('X'))) {
            throw new DukeException("Item is already marked as done, we cannot mark it as done again.\n");
        }
        return number - 1;
    }

    /**
     * A method that parses users inputs that wishes to delete a task
     * @param input the user's input
     * @return the index of the task user wishes to delete
     * @throws DukeException if the user's input is in an wrong format
     */
    public static int parseDelete(String input) throws DukeException {
        // int number = Integer.parseInt(String.valueOf(input.charAt(7)));
        String numberString = String.valueOf(input.charAt(7));
        if (!PATTERN.matcher(numberString).matches()) {
            throw new DukeException("Please enter \'delete [index of item]\' to mark item as done.\n");
        }
        int number = Integer.parseInt(String.valueOf(numberString));
        if (number > TaskList.getSize() || number < 0) {
            throw new DukeException("Item does not exist, we cannot delete it.\n");
        }
        return number - 1;
    }

    /**
     * A method that parses users inputs that wishes to add a todo task
     * @param input the user's input
     * @return the task that the user added
     * @throws DukeException if the user's input is in an wrong format
     */
    public static Task parseTodo(String input) throws DukeException {
        if (input.length() == 4) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n");
        }
        String taskDesc = input.substring(5);
        return new Todo(taskDesc);

    }

    /**
     * A method that parses users inputs that wishes to add a deadline task
     * @param input the user's input
     * @return the task that the user added
     * @throws DukeException if the user's input is in an wrong format
     */
    public static Task parseDeadline(String input) throws DukeException {
        if (input.length() == 8) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.\n");
        }
        int byIndex = input.indexOf("/");
        String by = input.substring(byIndex+4);
        String taskDesc = input.substring(9, byIndex-1);
        return new Deadline(taskDesc, by);
    }

    /**
     * A method that parses users inputs that wishes to add a event task
     * @param input the user's input
     * @return the task that the user added
     * @throws DukeException if the user's input is in an wrong format
     */
    public static Task parseEvent(String input) throws DukeException {
        if (input.length() == 5) {
            throw new DukeException("OOPS!!! The description of a event cannot be empty.\n");
        }
        int byIndex = input.indexOf("/");
        String by = input.substring(byIndex+4);
        String taskDesc = input.substring(6, byIndex-1);
        return new Event(taskDesc,by);
    }
}