package duke;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;


public class Parser {
    private final TaskList tasks;
    private final Storage storage;

    private final DateTimeFormatter fmt = new DateTimeFormatterBuilder()
            .appendPattern("d/M/yyyy")
            .optionalStart()
            .appendPattern(" HHmm")
            .optionalEnd()
            .parseDefaulting(ChronoField.HOUR_OF_DAY, 0)
            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
            .toFormatter();

    Parser(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Parses the input given and returns the responses given the input.
     *
     * @param input Received string from scanner
     * @return Response from program.
     */
    public String[] parseInput(String input) {
        try {
            if (input.startsWith("done")) {
                return setTaskAsDone(input);
            } else if (input.startsWith("delete")) {
                return deleteTask(input);
            } else if (input.startsWith("list")) {
                return list();
            } else if (input.startsWith("find")) {
                return find(input);
            } else if (input.startsWith("snooze")) {
                return snooze(input);
            } else {
                return addTask(input);
            }
        } catch (DukeException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Changes the time of the deadline or event.
     * Format: snooze {index} /to {time}
     *
     * @param input User command.
     * @return Replies from duke bot.
     * @throws IOException If can't write to file.
     */
    private String[] snooze(String input) throws IOException {
        String[] splitString = input.split(" ", 2);
        ArrayList<String> results = new ArrayList<>();
        if (splitString.length == 1) {
            return new String[]{"OOPS!!! You have not inputted a task or a rescheduled time"};
        }
        String[] splitSnooze = splitString[1].split("/to", 2);
        int taskIndex;
        try {
            taskIndex = Integer.parseInt(splitSnooze[0].trim());
        } catch (NumberFormatException e) {
            return displayIncorrectIndex();
        }
        if (taskIndex > tasks.size()) {
            return displayIncorrectIndex();
        }
        Task task = tasks.get(taskIndex - 1);
        results.add("\tYou have snoozed this task: ");
        results.add(task.toString() + " to:\n");
        LocalDateTime newDate;
        try {
            newDate = LocalDateTime.parse(splitSnooze[1].trim(), fmt);
        } catch (DateTimeParseException e) {
            return displayDateFormatError();
        }
        if (task instanceof Todo) {
            return new String[]{"OOPS!!! Todos don't have schedules"};
        }
        results.addAll(snoozeTask(task, taskIndex, newDate));
        return results.toArray(new String[0]);
    }
    private ArrayList<String> snoozeTask(Task task, int taskIndex, LocalDateTime newDate) {
        ArrayList<String> results = new ArrayList<>();
        try {
            if (task instanceof Deadline) {
                Deadline newDeadline = new Deadline(task.description, newDate);
                tasks.set(taskIndex - 1, newDeadline);
                storage.writeEntireFile();
                results.add(newDeadline.toString());
            }
            if (task instanceof Event) {
                Event newEvent = new Event(task.description, newDate);
                tasks.set(taskIndex - 1, newEvent);
                storage.writeEntireFile();
                results.add(newEvent.toString());
            }
        } catch (IOException e) {
            results.add("OOPS!!! There was an error processing your input. Please try again!");
        }
        return results;
    }
    /**
     * Adds task to file.
     * Format (todo): todo {description}
     * Format (deadline): deadline {description} /by {time}
     * Format (event): event {description} /at {time}
     *
     * @param input User command.
     * @return Replies from duke bot.
     * @throws IOException If can't write to file.
     */
    private String[] addTask(String input) throws DukeException, IOException {
        String[] splitString = input.split(" ", 2);
        String type = splitString[0];
        Task task;
        switch (type) {
        case "todo":
            try {
                task = processTask(splitString);
            } catch (DukeException e) {
                return new String[]{e.getMessage()};
            }
            break;
        case "deadline":
            try {
                task = processDeadline(splitString);
            } catch (DukeException e) {
                return new String[]{e.getMessage()};
            } catch (DateTimeParseException e) {
                return displayDateFormatError();
            }
            break;
        case "event":
            try {
                task = processEvent(splitString);
            } catch (DukeException e) {
                return new String[]{e.getMessage()};
            } catch (DateTimeParseException e) {
                return displayDateFormatError();
            }
            break;
        default:
            return new String[]{"OOPS!!! I'm sorry, but I don't know what that means :-("};
        }
        tasks.add(task);
        storage.write(task.save());
        return displayTasks();
    }

    Todo processTask(String[] input) throws DukeException {
        if (input.length == 1) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n");
        }
        return new Todo(input[1]);
    }
    Deadline processDeadline(String[] input) throws DukeException, DateTimeParseException {
        if (input.length == 1) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.\n");
        }
        String[] splitDeadline = input[1].split(" /by ", 2);
        if (splitDeadline.length == 1) {
            throw new DukeException("OOPS!!! Please follow this format: deadline <description> "
                    + "/by <date (DD/MM/YYYY HHMM)>");
        }

        LocalDateTime deadline = processDate(splitDeadline[1].trim());
        return new Deadline(splitDeadline[0], deadline);
    }

    Event processEvent(String[] input) throws DukeException, DateTimeParseException {
        if (input.length == 1) {
            throw new DukeException("OOPS! The description of an event cannot be empty.\n");
        }
        String[] splitEvent = input[1].split(" /at ", 2);
        if (splitEvent.length == 1) {
            throw new DukeException("OOPS!!! Please follow this format: event <description> "
                    + "/at <date (DD/MM/YYYY HHMM)>");
        }
        LocalDateTime deadline = processDate(splitEvent[1].trim());
        return new Event(splitEvent[0], deadline);
    }

    private LocalDateTime processDate(String stringDate) throws DateTimeParseException {
        return LocalDateTime.parse(stringDate, fmt);
    }

    private String[] displayDateFormatError() {
        return new String[]{"OOPS!!! Input your date in the following format: DD/MM/YYYY HHMM"};
    }
    private String[] displayTaskNotFound() {
        return new String[]{"OOPS!!! Task not found!"};
    }
    private String[] displayIncorrectIndex() {
        return new String[]{"OOPS!!! Please enter a valid index."};
    }
    private String[] displayTasks() {
        ArrayList<String> results = new ArrayList<>();
        results.add("\tGot it. I've added this task:\n\t\t" + tasks.get(tasks.size() - 1).toString()
                + "\n\tNow you have " + tasks.size() + " tasks in the list.");
        return results.toArray(new String[0]);
    }
    private String[] deleteTask(String input) {
        String[] splitString = input.split(" ", 2);
        if (splitString.length == 1) {
            return displayIncorrectIndex();
        }
        int index;
        try {
            index = Integer.parseInt(splitString[1]) - 1;
        } catch (NumberFormatException e) {
            return displayIncorrectIndex();
        }

        Task removedTask;
        if (index >= tasks.size() || index < 0) {
            return displayTaskNotFound();
        } else {
            removedTask = tasks.remove(index);
        }
        assert removedTask != null : "Task removed must exist!";
        try {
            storage.writeEntireFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String[]{"\tNoted. I've removed this task:\n" + "\t\t" + removedTask.toString()
                + "\n" + "\tNow you have " + tasks.size() + " tasks in the list."};
    }

    private String[] setTaskAsDone(String input) {
        String[] splitString = input.split(" ", 2);
        if (splitString.length == 1) {
            return displayIncorrectIndex();
        }
        int index;
        try {
            index = Integer.parseInt(splitString[1]) - 1;
        } catch (NumberFormatException e) {
            return displayIncorrectIndex();
        }

        if (index + 1 <= 0 || index + 1 > tasks.size()) {
            return displayTaskNotFound();
        }
        tasks.get(index).markAsDone();
        try {
            storage.writeEntireFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[]{"\tNice! I've marked this task as done:\n\t\t" + tasks.get(index).toString()};
    }

    private String[] list() {
        ArrayList<String> list = new ArrayList<>();
        list.add("\tHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            list.add("\t" + (i + 1) + ". " + tasks.get(i).toString());
        }
        return list.toArray(new String[0]);
    }

    private String[] find(String input) {
        String[] splitString = input.split(" ", 2);
        ArrayList<String> list = new ArrayList<>();
        list.add("\tHere are the matching tasks in your list:");
        if (splitString.length == 1) {
            for (int i = 0; i < tasks.size(); i++) {
                list.add("\t" + (i + 1) + ". " + tasks.get(i).toString());
            }
            return list.toArray(new String[0]);
        }
        String keyword = splitString[1];
        int currentIndex = 0;
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).toString().contains(keyword)) {
                list.add("\t" + (currentIndex + 1) + ". " + tasks.get(i).toString());
                currentIndex++;
            }
        }
        return list.toArray(new String[0]);
    }
}
