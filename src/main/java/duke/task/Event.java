package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exceptions.DucIncompleteException;
import duke.exceptions.DucInvalidFormatException;
import duke.exceptions.DucWrongCommandException;
import duke.main.Parser;

public class Event extends Task {

    private final String date;
    private final String taskName;

    /**
     * Constructor of an Event Task
     * @param taskName contains the task description and task time that
     *                 is to be divided
     */
    public Event(String taskName) {
        super(taskName.split("/at")[0].trim());
        this.taskName = taskName;
        if (taskName.length() == 0) {
            throw new DucIncompleteException();
        }
        if (!taskName.contains("/at") && taskName.contains("/by")) {
            throw new DucWrongCommandException("Deadline");
        } else if (!taskName.contains("/at")) {
            throw new DucWrongCommandException("Todo");
        }
        String[] taskComponents = taskName.split("/at");
        String taskTime = taskComponents[1].trim();
        try {
            this.date = Parser.convert(LocalDate.parse(taskTime));
        } catch (DateTimeParseException e) {
            throw new DucInvalidFormatException();
        }
    }

    /**
     * Saves task description to file on the initial date format
     * @return a String that represents user initial input
     */
    @Override
    public String save() {
        return this.toString().replaceAll(date, Parser.reverse(date));
    }

    /**
     * String representation of a Task
     * @return String of Task
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at " + date + ")";
    }

    @Override
    public String getDate() {
        return this.date;
    }

    @Override
    public Task completedTask() {
        Task newTask = new Event(taskName);
        newTask.markAsCompleted();
        return newTask;
    }
}
