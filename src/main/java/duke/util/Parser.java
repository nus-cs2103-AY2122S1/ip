package duke.util;

import duke.exceptions.DukeException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Parser class to handle user commands.
 */
public class Parser {
    private static boolean isExit = false;
    private static TaskList tasks;

    /**
     * Returns parsed string according to user's full command.
     *
     * @param command User's command input.
     * @return Respective command object.
     * @throws DukeException In case of errors.
     */
    public static String parse(String command) throws DukeException {
        if (command.equals("bye")) {
            return ExitCommand();
        } else if (command.equals("list")) {
            return ListCommand();
        } else {
            String[] instr = command.split(" ", 2);
            String instrType = instr[0];
            String instrContent = "";
            if (instr.length != 1) {
                instrContent = instr[1];
            }
            switch (instrType) { 
            case "todo":
            case "t":
                return ToDoCommand(instrContent);
            case "deadline":
            case "d":
                return DeadlineCommand(instrContent);
            case "event":
            case "e":
                return EventCommand(instrContent);
            case "done":
                return DoneCommand(instrContent);
            case "delete":
                return DeleteCommand(instrContent);
            case "find":
                return FindCommand(instrContent);
            default:
                throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    /**
     * Sets taskList for Parser.
     *
     * @param list TaskList that manages tasks.
     */
    public static void setTaskList(TaskList list) {
        tasks = list;
    }

    /**
     * Returns a boolean indicating whether program should exit.
     *
     * @param command Input command by the user.
     * @return Boolean indicator for whether the program should exit.
     */
    public static boolean shouldExit(String command) {
        return command.equals("bye");
    }
    
    /**
     * Handles the logic of exiting.
     *
     * @return The response string.
     */
    public static String ExitCommand() {
        try {
            Storage.writeToFile(tasks);
            isExit = true;
            return Ui.bye();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * Handles the logic of listing tasks.
     *
     * @return The response string.
     */
    public static String ListCommand() {
        return tasks.list();
    }

    /**
     * Handles the logic of adding to do tasks.
     *
     * @param command Details of to do command.
     * @return The response string.
     * @throws DukeException DukeException if command format for to do is wrong.
     */
    public static String ToDoCommand(String command) throws DukeException {
        if (command.equals("")) {
            throw new DukeException("Please specify the description of a to do. :)");
        } else {
            Todo todo = new Todo(command);
            tasks.addTask(todo);
            return tasks.formatAddedTask(todo);
        }
    }

    /**
     * Handles the logic of adding deadline tasks.
     *
     * @param command Details of deadline command.
     * @return The response string.
     * @throws DukeException DukeException if command format for deadline is wrong.
     */
    public static String DeadlineCommand(String command) throws DukeException {
        if (command.equals("")) {
            throw new DukeException("Please specify the description of a deadline. :)");
        } else {
            String[] deadlineArr = command.split(" /by ", 2);
            if (deadlineArr.length != 2) {
                throw new DukeException("Deadline has incorrect format.\n"
                        + "Please follow the format of the following example:\n"
                        + "deadline assignment /by 2021-11-02");
            } else {
                String deadlineTime = TimeExtractor.extractTime(deadlineArr[1]);
                Deadline deadline = new Deadline(deadlineArr[0], deadlineTime);
                tasks.addTask(deadline);
                return tasks.formatAddedTask(deadline);
            }
        }
    }

    /**
     * Handles the logic of adding event tasks.
     *
     * @param command Details of event command.
     * @return The response string.
     * @throws DukeException DukeException if command format for event is wrong.
     */
    public static String EventCommand(String command) throws DukeException {
        if (command.equals("")) {
            throw new DukeException("Please specify the description of an event. :)");
        } else {
            String[] eventArr = command.split(" /at ", 2);
            if (eventArr.length != 2) {
                throw new DukeException("Event has incorrect format.\n"
                        + "Please follow the format of the following example:\n"
                        + "event concert /at 2021-11-26");
            } else {
                String eventTime = TimeExtractor.extractTime(eventArr[1]);
                Event event = new Event(eventArr[0], eventTime);
                tasks.addTask(event);
                return tasks.formatAddedTask(event);
            }
        }
    }
    
    /**
     * Handles the logic of finishing tasks.
     *
     * @param command Details of done command.
     * @return The response string.
     * @throws DukeException DukeException if command format for done is wrong.
     */
    public static String DoneCommand(String command) throws DukeException {
        if (command.equals("")) {
            throw new DukeException("Please specify which task to mark as done. :)");
        } else {
            int index = Integer.parseInt(command);
            if (index <= tasks.getListSize()) {
                return tasks.setAsDone(index);
            } else {
                throw new DukeException("You only have " + tasks.getListSize() + " tasks now."
                        + " Please give a number within this range");
            }
        }
    }

    /**
     * Handles the logic of deleting tasks.
     *
     * @param command Details of delete command.
     * @return The response string.
     * @throws DukeException DukeException if command format for delete is wrong.
     */
    public static String DeleteCommand(String command) throws DukeException {
        if (command.equals("")) {
            throw new DukeException("Please specify which task to delete. :)");
        } else {
            int index = Integer.parseInt(command);
            if (index <= tasks.getListSize()) {
                return tasks.deleteTask(index);
            } else {
                throw new DukeException("You only have " + tasks.getListSize() + " tasks now."
                        + " Please give a number within this range");
            }
        }
    }

    /**
     * Handles the logic of finding matching tasks.
     *
     * @param command Details of find command.
     * @return The response string.
     */
    public static String FindCommand(String command) {
        return tasks.showFind(command);
    }

    /**
     * Returns whether the program should stop.
     *
     * @return A boolean indicator for whether a program should stop.
     */
    public static boolean isExit() {
        return isExit;
    }

}
