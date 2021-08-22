package duke.tasklist;

import duke.exception.DukeException;
import duke.exception.Messages;
import duke.storage.Storage;

import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a list of task within chat bot.
 * Contains operations to add, delete and run task.
 */
public class TaskList {
    private final List<String> tasks;
    private final List<Task> library;

    /**
     * Constructs TaskList Object.
     */
    public TaskList() {
        this.library = new ArrayList<>(100);
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs TaskList Object.
     * Loads task request from text file into list.
     *
     * @param taskFile scanner object of text file.
     */
    public TaskList(Scanner taskFile) {
        this.library = new ArrayList<>(100);
        this.tasks = new ArrayList<>();

        // Extract lines
        while(taskFile.hasNext()) {
            String str = taskFile.nextLine();
            tasks.add(str);
        }
        taskFile.close();
    }

    /**
     * Returns task title of task request.
     * Substrings input line after start word till end of string.
     *
     * @param input task request line.
     * @param start substring after target word.
     * @throws DukeException If start word doesn't exist.
     */
    private String cut(String input, String start) throws DukeException {
        String result;
        try {
            result = input.substring(input.indexOf(start) + start.length() + 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(String.format(Messages.EMPTY.toString(), start));
        }
        return result;
    }

    /**
     * Returns task title of task request.
     * Substrings input line between start word and end word.
     *
     * @param input task request line.
     * @param start substring after target word.
     * @param end substring before target word.
     * @throws DukeException If start or end word doesn't exist.
     */
    private String cut(String input, String start, String end) throws DukeException {
        String result;
        try {
            result = input.substring(input.indexOf(start) + start.length() + 1, input.indexOf(end));
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(String.format(Messages.EMPTY.toString(), start));
        }
        return result;
    }

    /**
     * Converts date time string into LocalDateTime.
     * Parses date time by d/M/yyyy (optional HHmm).
     * If optional is empty, default time is 2359.
     *
     * @param time date time string.
     */
    private LocalDateTime dateTime(String time) {
        DateTimeFormatter fmt = new DateTimeFormatterBuilder()
                .appendPattern("d/M/yyyy")
                .optionalStart()
                .appendPattern(" HHmm")
                .optionalEnd()
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 23)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59)
                .toFormatter();

        return LocalDateTime.parse(time, fmt);
    }

    /**
     * Adds new task command into TaskList.
     *
     * @param tsk task to be inserted.
     */
    public void add(String tsk) {
        tasks.add(tsk);
    }

    /**
     * Removes new task command into TaskList.
     *
     * @param tsk task to be removed.
     * @throws DukeException If target task doesn't exist in list.
     */
    public void remove(String tsk) throws DukeException {
        boolean success = tasks.remove(tsk);
        if (!success)
            throw new DukeException("Task not found");
    }

    /**
     * Executes each command on TaskList.
     * If command is list, display all task.
     * If command is bye, exits TaskList program.
     * If command is done, marks task as completed.
     * If command is todo, adds Todo task to list.
     * If command is deadline, adds Deadline task to list.
     * If command is event, adds Event task to list.
     * If command is delete, removes task from list.
     */
    public void execute() {
        for (String input: tasks) {
            String output = "";
            try {
                if (input.equals("list")) {
                    output += "Here are the tasks in your list:\n";

                    int count = 1;
                    for (Task task : library) {
                        output += String.format("%d.%s\n", count++, task);
                    }
                } else if (input.equals("bye")) {
                    break;
                } else if (input.contains("done")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;

                    Task target = library.get(index);
                    target.setDone();

                    output += "Nice! I've marked this task as done:\n"
                            + target + "\n";
                } else if (input.contains("todo")) {
                    String name = cut(input, "todo");

                    Todo newTodo = new Todo(name);
                    library.add(newTodo);

                    output += "Got it. I've added this task:\n"
                            + newTodo + "\n"
                            + String.format("Now you have %d tasks in the list.\n", library.size());
                } else if (input.contains("deadline")) {
                    String name = cut(input, "deadline", "/by");
                    LocalDateTime time = dateTime(cut(input, "/by"));

                    Deadline newDeadline = new Deadline(name, time);
                    library.add(newDeadline);

                    output += "Got it. I've added this task:\n"
                            + newDeadline + "\n"
                            + String.format("Now you have %d tasks in the list.\n", library.size());
                } else if (input.contains("event")) {
                    String name = cut(input, "event", "/at");
                    LocalDateTime time = dateTime(cut(input, "/at"));

                    Event newEvent = new Event(name, time);
                    library.add(newEvent);

                    output += "Got it. I've added this task:\n"
                            + newEvent + "\n"
                            + String.format("Now you have %d tasks in the list.\n", library.size());
                } else if (input.contains("delete")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;

                    Task target = library.remove(index);

                    output += "Noted. I've removed this task:\n"
                            + target + "\n"
                            + String.format("Now you have %d tasks in the list.\n", library.size());
                } else {
                    throw new DukeException(Messages.KNOWN.toString());
                }

                Storage.save(library);

            } catch (DukeException | IOException e) {
                output += "☹ OOPS!!! " + e.getMessage() + "\n";
            } catch (IndexOutOfBoundsException e) {
                output += "☹ OOPS!!! "+ Messages.EXIST + "\n";
            } catch (DateTimeParseException e) {
                output += "☹ OOPS!!! "+ Messages.TIME + "\n";
            }
            System.out.println(output);
        }
    }
}
