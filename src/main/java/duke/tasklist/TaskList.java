package duke.tasklist;

import duke.exception.DukeException;
import duke.exception.Messages;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    private final List<Task> library;

    public TaskList() {
        this.library = new ArrayList<>(100);
    }

    public TaskList(Scanner taskFile) throws DukeException{
        this.library = new ArrayList<>(100);

        // Extract lines
        while(taskFile.hasNext()) {
            String str = taskFile.nextLine();
            add(str);
        }
        taskFile.close();
    }

    private String cut(String input, String start) throws DukeException {
        String result;
        try {
            result = input.substring(input.indexOf(start) + start.length() + 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(String.format(Messages.EMPTY.toString(), start));
        }
        return result;
    }

    private String cut(String input, String start, String end) throws DukeException {
        String result;
        try {
            result = input.substring(input.indexOf(start) + start.length() + 1, input.indexOf(end));
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(String.format(Messages.EMPTY.toString(), start));
        }
        return result;
    }

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

    public String remove(int index) {
        Task tsk = library.remove(index);
        return tsk.toString();
    }

    public String done(int index) {
        Task target = library.get(index);
        target.setDone();

        return target.toString();
    }

    public String list() {
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < library.size(); i++) {
            Task tsk = library.get(i);
            output.append(String.format("%d.%s\n", i + 1, tsk));
        }
        return output.toString().trim();
    }

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
