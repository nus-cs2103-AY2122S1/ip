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

public class TaskList {
    private final List<String> tasks;
    private final List<Task> library;

    public TaskList() {
        this.library = new ArrayList<>(100);
        this.tasks = new ArrayList<>();
    }

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

    public void add(String tsk) {
        tasks.add(tsk);
    }

    public void remove(String tsk) throws DukeException {
        boolean success = tasks.remove(tsk);
        if (!success)
            throw new DukeException("Task not found");
    }

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
