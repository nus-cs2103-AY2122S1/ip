package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.exceptions.DucIncompleteException;
import duke.exceptions.DucInvalidFormatException;
import duke.exceptions.DucWrongCommandException;
import duke.main.Parser;


public class Deadline extends Task {

    private final String date;
    private final String taskName;

    /**
     * Constructor for a Deadline Task
     * @param taskName description of the task, to be divided into the
     *                 name and the time of task
     */
    public Deadline(String taskName) {
        super(taskName.split("/by")[0].trim());
        this.taskName = taskName;
        if (this.taskName.length() == 0) {
            throw new DucIncompleteException();
        }
        if (!taskName.contains("/by") && taskName.contains("/at")) {
            throw new DucWrongCommandException("Event");
        } else if (!taskName.contains("/by")) {
            throw new DucWrongCommandException("Todo");
        }
        String[] taskComponents = taskName.split("/by");
        String taskTime = taskComponents[1].trim();
        try {
            this.date = Parser.convert(LocalDate.parse(taskTime));
        } catch (DateTimeParseException e) {
            throw new DucInvalidFormatException();
        }
    }

    /**
     * Save task description to file on the initial date format
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
        return "[D]" + super.toString() + " (by " + date + ")";
    }

    @Override
    public String getDate() {
        return this.date;
    }

    @Override
    public Task completedTask() {
        Task newTask = new Deadline(taskName);
        newTask.markAsCompleted();
        return newTask;
    }
}
