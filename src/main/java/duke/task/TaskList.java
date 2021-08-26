package duke.task;

import duke.util.DukeException;
import duke.util.Parser;
import duke.util.Ui;

import java.util.ArrayList;
import java.util.List;

import java.util.function.Consumer;

import java.util.stream.Collectors;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public void forEach(Consumer<? super Task> consumer) {
        tasks.forEach(consumer);
    }

    public TaskList addTask(String input, Ui ui) {
        List<Task> newTasks = new ArrayList<>(tasks);
        if (input.contains("|")) {
            throw new DukeException("Input contains |, which is an invalid character.");
        }
        Task task;
        String commandString = input.split(" ")[0].toUpperCase();
        switch (commandString) {
        case "TODO":
            // Add duke.task.Todo task
            if (input.length() <= 5) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            task = new Todo(input.substring(5));
            break;
        case "DEADLINE":
            // Add duke.task.Deadline task
            String errorMessage = "duke.command.Command must be in the format: [taskName] /by "
                    + "[date(YYYY-MM-DD)] [time(HH:MM)].";
            String[] splitInput = Parser.splitWith(input, 9, " /by ", errorMessage);
            String taskName = splitInput[0];
            errorMessage = "Date and time must be in the format: YYYY-MM-DD HH:MM.";
            String[] dateTime = Parser.splitWith(splitInput[1], 0, " ", errorMessage);
            String date = dateTime[0];
            String time = dateTime[1];
            task = new Deadline(taskName, Parser.parseDateFromInput(date), Parser.parseTimeFromInput(time));
            break;
        default: // default is guaranteed to be event task due to use of enum + outer control flow
            // Add duke.task.Event task
            errorMessage = "duke.command.Command must be in the format: [taskName] /at "
                    + "[date(YYYY-MM-DD)] [start time(HH:MM)] [end time(HH:MM)].";
            splitInput = Parser.splitWith(input, 6, " /at ", errorMessage);
            taskName = splitInput[0];
            errorMessage = "Date and times must be in the format: YYYY-MM-DD HH:MM HH:MM.";
            dateTime = Parser.splitWith(splitInput[1], 0, " ", errorMessage);
            // If user only input in one time
            if (dateTime.length < 3) {
                throw new DukeException(errorMessage);
            }
            date = dateTime[0];
            String startTime = dateTime[1];
            String endTime = dateTime[2];
            task = new Event(taskName, Parser.parseDateFromInput(date),
                    Parser.parseTimeFromInput(startTime), Parser.parseTimeFromInput(endTime));
            break;
        }

        // Common functionality: add task to list, print task and list size, save tasks to file
        newTasks.add(task);
        ui.showMessage("Got it. The following task has been added: ");
        ui.showIndentedMessage(task.toString());
        ui.showMessage(String.format("Now you have %d task%s in the list.",
                newTasks.size(), newTasks.size() == 1 ? "" : "s"));
        return new TaskList(newTasks);
    }

    public TaskList deleteTask(String input, Ui ui) {
        List<Task> newTasks = new ArrayList<>(tasks);
        if (input.length() <= 7) {
            throw new DukeException("Please type in a task number to delete.");
        }
        String taskNumberString = input.substring(7);
        if (taskNumberString.matches("\\d+")
                && (Integer.parseInt(taskNumberString) - 1 < tasks.size()
                && Integer.parseInt(taskNumberString) - 1 >= 0)) {
            int taskIndex = Integer.parseInt(taskNumberString) - 1;
            Task removedTask = newTasks.remove(taskIndex);
            ui.showMessage("Got it. The following task has been removed:");
            ui.showIndentedMessage(removedTask.toString());
            ui.showMessage(String.format("Now you have %d task%s in the list.",
                    newTasks.size(), newTasks.size() == 1 ? "" : "s"));
        } else {
            // Invalid input (not a number or invalid number)
            throw new DukeException("Please type in a valid task number to delete.");
        }
        return new TaskList(newTasks);
    }

    public TaskList markTask(String input, Ui ui) {
        List<Task> newTasks = new ArrayList<>(tasks);
        if (input.length() <= 5) {
            throw new DukeException("Please type in a task number to mark as done.");
        }
        String taskNumberString = input.substring(5);
        if (taskNumberString.matches("\\d+")
                && (Integer.parseInt(taskNumberString) - 1 < tasks.size()
                && Integer.parseInt(taskNumberString) - 1 >= 0)) {
            int taskIndex = Integer.parseInt(taskNumberString) - 1;
            Task doneTask = newTasks.get(taskIndex);
            ui.showMessage("Good work! This task is now marked as done:");
            ui.showIndentedMessage(doneTask.toString());
            newTasks.set(taskIndex, doneTask.markAsDone());
        } else {
            // Invalid input (not a number or invalid number)
            throw new DukeException("Please type in a valid task number to mark as done.");
        }
        return new TaskList(newTasks);
    }

    public TaskList getDueTasks(String input) {
        // Check if input is valid and input number is an integer
        if (input.length() <= 4 || !input.substring(4, input.length() - 1).matches("\\d+")) {
            throw new DukeException("duke.command.Command must be of the form: due [integer][h/d/m] "
                    + "(h = hours, d = days, m = months)");
        }

        String offset = input.substring(4, input.length() - 1);
        LocalDateTime dateTime = LocalDateTime.now();
        switch (input.charAt(input.length() - 1)) {
        case ('h'):
            dateTime = dateTime.plusHours(Integer.parseInt(offset));
            break;
        case ('d'):
            dateTime = dateTime.plusDays(Integer.parseInt(offset));
            break;
        case ('m'):
            dateTime = dateTime.plusMonths(Integer.parseInt(offset));
            break;
        default:
            throw new DukeException("duke.command.Command must be of the form: due [integer][h/d/m] "
                    + "(h = hours, d = days, m = months)");
        }

        // Copy dateTime to an effectively final variable for use in lambda
        LocalDateTime finalDateTime = dateTime;
        List<Task> beforeDateTasks = tasks.stream()
                .filter(task -> task.isBeforeDate(finalDateTime))
                .collect(Collectors.toList());
        return new TaskList(beforeDateTasks);
    }

    public TaskList getOnDateTasks(String input) {
        if (input.length() <= 7) {
            throw new DukeException("Date must be of the form YYYY-MM-DD, and must be a real/valid date.");
        }
        String dateString = input.substring(7);
        LocalDate date = Parser.parseDateFromInput(dateString);
        List<Task> onDateTasks = tasks.stream()
                .filter(task -> task.hasSameDate(date))
                .collect(Collectors.toList());
        return new TaskList(onDateTasks);
    }

    // Returns list in numbered format (1., 2., 3., etc.)
    // Example string format is: \t[1.task name]\n
    //                           \t\t[2.task name]\n
    //                           \t\t[3.task name]
    @Override
    public String toString() throws DukeException {
        if (tasks.size() == 0) {
            return "No tasks found.";
        }
        StringBuilder listString = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            if (i == tasks.size() - 1) {
                listString.append(String.format("\t\t%d.%s", i + 1, tasks.get(i)));
            } else {
                listString.append(String.format("\t\t%d.%s\n", i + 1, tasks.get(i)));
            }
        }
        return listString.toString().trim();
    }
}
