package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private TaskList list;

    public Parser(TaskList list) {
        this.list = list;
    }

    public void parse(String input) throws DukeException {
        if (input.equals("list")) {
            list.printTasks();
        } else if (input.startsWith("done")) {
            try {
                int listIndex = Integer.parseInt(input.substring(5));
                if (listIndex <= list.size() && listIndex >= 1) {
                    list.completeTask(listIndex);
                } else {
                    throw new DukeException("Couldn't find that task in the list! Try again.");
                }
            } catch (NumberFormatException e) {
                throw new DukeException("Please make sure only a number follows the command 'done'. Try again.");
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please add a number after the command 'done'. Try again.");
            }
        } else if (input.startsWith("deadline")) {
            if (!input.contains("/by")) {
                throw new DukeException("Please state the deadline for this task with /by! Try again.");
            } else {
                try {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy");
                    int deadlineIndex = input.indexOf("/by") + 4;
                    String deadlineString = input.substring(deadlineIndex);
                    LocalDate deadline = LocalDate.parse(deadlineString, dtf);
                    String firstTrimmed = input.substring(9);
                    String lastTrimmed = firstTrimmed.substring(0, firstTrimmed.indexOf("/by") - 1);
                    Deadline createdDeadlineTask = new Deadline(lastTrimmed, false, deadline);
                    list.addToList(createdDeadlineTask);
                    System.out.println("Got it. I've added this task:\n" + "  " + createdDeadlineTask + "\n"
                            + "Now you have " + list.size() + " task" + (list.size() == 1 ? "" : "s") +
                            " in the list.");
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("Please ensure that there is a task description and deadline. " +
                            "Try again.");
                } catch (DateTimeParseException e) {
                    throw new DukeException("Please ensure that your deadline is formatted in the following " +
                            "way: DD/MM/YY");
                }
            }
        } else if (input.startsWith("todo")) {
            try {
                String trimmed = input.substring(5);
                Todo createdTodoTask = new Todo(trimmed, false);
                list.addToList(createdTodoTask);
                System.out.println("Got it. I've added this task:\n" + "  " + createdTodoTask + "\n"
                        + "Now you have " + list.size() + " task" + (list.size() == 1 ? "" : "s") +
                        " in the list.");
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please add the task information. Try again.");
            }
        } else if (input.startsWith("event")) {
            if (!input.contains("/at")) {
                throw new DukeException("Please state when the event will be held with /at! Try again.");
            } else {
                try {
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
                    int atIndex = input.indexOf("/at") + 4;
                    String atString = input.substring(atIndex);
                    String firstTrimmed = input.substring(6);
                    String lastTrimmed = firstTrimmed.substring(0, firstTrimmed.indexOf("/at") - 1);
                    LocalDateTime at = LocalDateTime.parse(atString, dtf);
                    Event createdEventTask = new Event(lastTrimmed, false, at);
                    list.addToList(createdEventTask);
                    System.out.println("Got it. I've added this task:\n" + "  " + createdEventTask + "\n"
                            + "Now you have " + list.size() + " task" + (list.size() == 1 ? "" : "s") +
                            " in the list.");
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException("Please ensure that there is an event time. Try again.");
                } catch (DateTimeParseException e) {
                    throw new DukeException("Please ensure that your event is formatted in the following " +
                            "way: DD/MM/YY HHmm (24 hr format)");
                }
            }
        } else if (input.startsWith("delete")) {
            try {
                int toDeleteIndex = Integer.parseInt(input.substring(7));
                list.deleteTask(toDeleteIndex);
            } catch (NumberFormatException e) {
                throw new DukeException("Please make sure only a number follows the command 'delete'. " +
                        "Try again.");
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please add a number after the command 'delete'. Try again.");
            }
        } else {
            throw new DukeException("I didn't quite get what you meant. To add a task, begin with " +
                    "'deadline', 'event' or 'todo'.");
        }
    }
}
