package duke.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.exception.Messages;

/**
 * Represents a list of task within chat bot.
 * Contains operations to add, delete and run task.
 */
public class TaskList {
    private final List<Task> library;

    /**
     * Constructs TaskList Object.
     */
    public TaskList() {
        this.library = new ArrayList<>(100);
    }

    /**
     * Constructs TaskList Object.
     * Loads task request from text file into list.
     *
     * @param taskFile scanner object of text file.
     */
    public TaskList(Scanner taskFile) throws DukeException {
        this.library = new ArrayList<>(100);

        // Extract lines
        while (taskFile.hasNext()) {
            String str = taskFile.nextLine();
            add(str);
        }
        taskFile.close();
    }

    /**
     * Returns task title of task request.
     * Substrings input line after start word till end of string.
     *
     * @param input task request line.
     * @param start substring after target word.
     * @return substring-ed title.
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
     * Returns task field of task request.
     * Substrings input line between start word and end word.
     *
     * @param input task request line.
     * @param start substring after target word.
     * @param end substring before target word.
     * @return substring-ed task field.
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
     * @return datetime of date time string.
     */
    private LocalDateTime dateTime(String time) throws DukeException {
        DateTimeFormatter fmt = new DateTimeFormatterBuilder()
                .appendPattern("d/M/yyyy")
                .optionalStart()
                .appendPattern(" HHmm")
                .optionalEnd()
                .parseDefaulting(ChronoField.HOUR_OF_DAY, 23)
                .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 59)
                .toFormatter();

        try {
            return LocalDateTime.parse(time, fmt);
        } catch (Exception e) {
            throw new DukeException(Messages.TIME.toString());
        }
    }

    /**
     * Adds new task into TaskList.
     *
     * @param input task to be inserted.
     * @return added task description.
     */
    public String add(String input) throws DukeException {
        Task tsk;
        if (input.contains("todo")) {
            String name = cut(input, "todo");

            tsk = new Todo(name);
        } else if (input.contains("deadline")) {
            String name = cut(input, "deadline", "/by");
            LocalDateTime time = dateTime(cut(input, "/by"));

            tsk = new Deadline(name, time);
        } else if (input.contains("event")) {
            String name = cut(input, "event", "/at");
            LocalDateTime time = dateTime(cut(input, "/at"));

            tsk = new Event(name, time);
        } else {
            throw new DukeException("No such task.");
        }

        library.add(tsk);
        return tsk.toString();
    }

    /**
     * Removes target task from TaskList.
     *
     * @param index task to be removed.
     * @return returned task description.
     */
    public String remove(int index) {
        Task tsk = library.remove(index);
        return tsk.toString();
    }

    /**
     * Marks target task as complete.
     *
     * @param index task to complete.
     * @return marked task description.
     */
    public String done(int index) {
        Task target = library.get(index);
        target.setDone();

        return target.toString();
    }

    /**
     * Returns list of task in TaskList.
     *
     * @return list of task.
     */
    public String list() {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < library.size(); i++) {
            Task tsk = library.get(i);
            output.append(String.format("%d.%s\n", i + 1, tsk));
        }
        return output.toString().trim();
    }

    /**
     * Returns task that has search term in name.
     *
     * @param search search term.
     * @return list of found task.
     */
    public String find(String search) {
        StringBuilder output = new StringBuilder();

        int count = 1;
        for (Task tsk : library) {
            if (tsk.getName().contains(search)) {
                output.append(String.format("%d.%s\n", count++, tsk));
            }
        }
        return output.toString().trim();
    }

    public List<Task> getTasks() {
        return library;
    }

    public int getSize() {
        return library.size();
    }
}
