package duke.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import duke.exception.DukeException;
import duke.exception.Messages;
import duke.parser.Cutter;

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
     * @throws DukeException if unable to add task.
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
     * Converts date time string into LocalDateTime.
     * Parses date time by d/M/yyyy (optional HHmm).
     * If optional is empty, default time is 2359.
     *
     * @param time date time string.
     * @return datetime of date time string.
     * @throws DukeException if failed datetime parsing
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
     * @throws DukeException if unable to add task
     */
    public String add(String input) throws DukeException {
        Task tsk;
        int oldSize = getSize();

        if (input.contains("todo")) {
            String name = Cutter.cut(input, "todo");

            tsk = new Todo(name);
        } else if (input.contains("deadline")) {
            String name = Cutter.cut(input, "deadline", "/by");
            LocalDateTime time = dateTime(Cutter.cut(input, "/by"));

            tsk = new Deadline(name, time);
        } else if (input.contains("event")) {
            String name = Cutter.cut(input, "event", "/at");
            LocalDateTime time = dateTime(Cutter.cut(input, "/at"));

            tsk = new Event(name, time);
        } else {
            throw new DukeException("No such task.");
        }

        library.add(tsk);
        int newSize = getSize();
        assert (newSize - oldSize) == 1 : "TaskList size should increase by 1";
        return tsk.toString();
    }

    /**
     * Removes target task from TaskList.
     *
     * @param index task to be removed.
     * @return returned task description.
     * @throws DukeException if unable to remove task
     */
    public String remove(int index) throws DukeException {
        try {
            int oldSize = getSize();

            Task tsk = library.remove(index);

            int newSize = getSize();
            assert (newSize - oldSize) == -1 : "TaskList size should decrease by 1";
            return tsk.toString();
        } catch (Exception e) {
            throw new DukeException("Unable to delete target task");
        }

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

        for (int i = 0; i < getSize(); i++) {
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

        List<Task> found = library.stream()
                .filter(tsk -> tsk.getName().contains(search))
                .collect(Collectors.toList());

        int count = 1;
        for (Task tsk : found) {
            output.append(String.format("%d.%s\n", count++, tsk));
        }
        assert count <= getSize() : "Find should be a subset of List";
        return output.toString().trim();
    }

    /**
     * Returns list of task sorted according to field in TaskList.
     *
     * @param input sort terms.
     * @return list of sorted task.
     */
    public String sort(String input) {
        String taskType = input.trim();

        StringBuilder output = new StringBuilder();
        List<Task> sortedTasks = new ArrayList<>();

        if (taskType.contains("deadline")) {
            sortedTasks = library.stream()
                    .filter(tsk -> tsk.getClass() == Deadline.class)
                    .map(tsk -> (Deadline) tsk)
                    .sorted(Comparator.comparing(Deadline::getDate))
                    .collect(Collectors.toList());
        } else if (taskType.contains("event")) {
            sortedTasks = library.stream()
                    .filter(tsk -> tsk.getClass() == Event.class)
                    .map(tsk -> (Event) tsk)
                    .sorted(Comparator.comparing(Event::getDate))
                    .collect(Collectors.toList());
        } else if (taskType.contains("name")) {
            sortedTasks = library.stream()
                    .sorted(Comparator.comparing(Task::getName))
                    .collect(Collectors.toList());
        } else if (taskType.contains("todo")) {
            sortedTasks = library.stream()
                    .filter(tsk -> tsk.getClass() == Todo.class)
                    .map(tsk -> (Todo) tsk)
                    .sorted(Comparator.comparing(Task::getName))
                    .collect(Collectors.toList());
        }

        int count = 1;
        for (Task tsk : sortedTasks) {
            output.append(String.format("%d.%s\n", count++, tsk));
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
