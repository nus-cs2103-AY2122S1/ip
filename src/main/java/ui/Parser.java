package ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;


public class Parser {
    /**
     * Reformats the input given by the user to sort into a particular task type to be returned.
     * Checks for incorrectly formatted input from user to be caught as an exception.
     *
     * @param taskName The name of the task
     * @param taskType The type of task
     * @return A task of class taskType based on the command given
     */
    public static Task parseStringIntoTask(String taskName, String taskType, boolean isDone, LogMessage returnMsg) {
        String split = "";

        try {
            if (taskName.equals("")) {
                String errorMsg = String.format("Oops!!! %s cannot be empty", taskType.toUpperCase());
                throw new DukeException.InsufficientArgumentsException(errorMsg);
            }
        } catch (DukeException e) {
            logError(e, returnMsg);
            return null;
        }

        try {
            switch (taskType) {
            case "todo":
                return new Todo(taskName, isDone);
            case "deadline":
                split = "/by ";
                break;
            case "event":
                split = "/at ";
                break;
            default:
                throw new DukeException.TaskTypeNotFoundException("TaskType cannot be found");
            }
            Task task = Parser.createTask(taskName, split, taskType, isDone);
            return task;
        } catch (DukeException e) {
            logError(e, returnMsg);
            return null;
        }
    }

    private static Task createTask(String taskName, String split, String taskType, boolean isDone) throws DukeException {
        if (!taskName.contains(split)) {
            String errorMsg = String.format("Oops!!! %s cannot be found in %s.", split, taskType);
            throw new DukeException.InsufficientArgumentsException(errorMsg);
        }

        String[] nameNTime = taskName.split(split);
        String name = nameNTime[0];
        String time;
        try {
            time = LocalDate.parse(nameNTime[1]).format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            time = nameNTime[1];
        }
        Task task = null;

        if (taskType.equals("deadline")) {
            task = new Deadline(name, time, isDone);
        } else if (taskType.equals("event")) {
            task = new Event(name, time, isDone);
        }
        return task;
    }

    private static void logError(DukeException e, LogMessage returnMsg) {
        returnMsg.add(e.getMessage());
        System.out.println(e.getMessage());
        Ui.printBreakline();
    }

}

