package duke;

import java.time.DateTimeException;

/**
 * Represents a class that handles all the input command.
 */
public class Parser {
    /**
     * Handles the input commands and calls the correct commands.
     *
     * @param list The current todolist.
     * @param input The command given by the user.
     * @return The response in String after the action is executed.
     * @throws DukeException If input do not meet the requirements.
     */
    public String process(String input, List list) throws DukeException {
        String[] split = input.split(" ", 2);
        assert split[0] != "list" : "The command should not be list";
        if (split[0].equals("done")) {
            return list.done(split);
        } else if (split[0].equals("help")) {
            return list.moreHelp(split[1]);
        } else if (split[0].equals("delete")) {
            return list.delete(split);
        } else if (split[0].equals("find")) {
            return list.search(split[1]);
        } else if (split[0].equals("todo")) {
            return processTodo(input, list);
        } else if (split[0].equals("deadline")) {
            return processDeadline(input, list);
        } else if (split[0].equals("event")) {
            return processEvent(input, list);
        } else {
            throw new DukeException();
        }
    }

    /**
     * Processes todos commands.
     *
     * @param input The command given by the user.
     * @param list The current todolist.
     * @return The response from duke.
     * @throws DukeTodoException If the input is not correctly formatted.
     */
    public String processTodo(String input, List list) throws DukeTodoException {
        String[] split = input.split(" ", 2);
        if (split.length == 1) {
            throw new DukeTodoException();
        }
        Task newItem = new Todo(split[1]);
        list.getTodos().add(newItem);
        return list.echo(newItem);
    }

    /**
     * Processes deadline commands.
     *
     * @param input The command given by the user.
     * @param list The current todolist.
     * @return The response from duke.
     * @throws DukeDeadlineException If the input is not correctly formatted.
     */
    public String processDeadline(String input, List list) throws DukeDeadlineException {
        String[] split = input.split(" ", 2);
        if (split.length == 1) {
            throw new DukeDeadlineException();
        }
        try {
            Task newItem = new Deadline(split[1]);
            list.getTodos().add(newItem);
            return list.echo(newItem);
        } catch (DateTimeException e) {
            return ("Please enter the date in yyyy-mm-dd format!");
        }
    }

    /**
     * Processes event commands.
     *
     * @param input The command given by the user.
     * @param list The current todolist.
     * @return The response from duke.
     * @throws DukeEventException If the input is not correctly formatted.
     */
    public String processEvent(String input, List list) throws DukeEventException {
        String[] split = input.split(" ", 2);
        if (split.length == 1) {
            throw new DukeEventException();
        }
        Task newItem = new Event(split[1]);
        list.getTodos().add(newItem);
        return list.echo(newItem);
    }
}
