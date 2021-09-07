package duke.task;


import java.time.LocalDateTime;

import duke.DukeException;


/**
 * Represents a user's task. Contains mainly a description of the task as well as other useful information.
 *
 * @author Aiken Wong
 */
public class Task implements Comparable<Task> {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes a Task. Note that taskFactory method should be used instead.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * Gets description of the task.
     *
     * @return Description.
     */
    public String getDescription() {
        return description.substring(0);
    }


    /**
     * Information on whether the task has been completed.
     *
     * @return Information on task completion.
     */
    public boolean getIsDone() {
        return isDone ? true : false;
    }

    /**
     * The main factory constructor method for Todo, Deadline and Event instances.
     *
     * @param taskType Enum representing the type of task to be initialized.
     * @param input    The raw input command from the user.
     * @return Task constructed based of input command.
     * @throws DukeException
     */
    public static Task of(TaskType taskType, String input) throws DukeException {
        Task newTask;
        if (taskType == taskType.TODO) {

            String description = input.substring(4).trim();
            if (description.isBlank()) {
                throw new DukeException("Please provide a description of the todo Sir/Mdm.");
            }
            newTask = new Todo(description);

        } else if (taskType == taskType.DEADLINE) {
            int index = input.indexOf("/by");
            if (index == -1) {
                if (input.equals("deadline")) {
                    throw new DukeException("Please provide a description and date of the deadline Sir/Mdm.");
                } else {
                    throw new DukeException("Please provide a date of the deadline Sir/Mdm.");
                }
            }
            String description = input.substring(9, index).trim();
            if (description.isBlank()) {
                throw new DukeException("Please provide a description of the deadline Sir/Mdm.");
            }
            String date = input.substring(index + 3).trim();
            newTask = Deadline.of(description, date);

        } else if (taskType == taskType.EVENT) {
            int index = input.indexOf("/at");
            if (index == -1) {
                if (input.equals("event")) {
                    throw new DukeException("Please provide a description and timeline of the event Sir/Mdm.");
                } else {
                    throw new DukeException("Please provide a timeline of the event Sir/Mdm.");
                }
            }

            String description = input.substring(5, index).trim();
            if (description.isBlank()) {
                throw new DukeException("Please provide a description of the event Sir/Mdm.");
            }
            String date = input.substring(index + 3).trim();
            newTask = Event.of(description, date);
        } else {
            throw new DukeException("Something wrong happened: Unknown task type given");
        }
        return newTask;
    }

    /**
     * Returns an enum representing the type of this Task.
     *
     * @return
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }


    /**
     * Marks the Task instance as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Returns the LocalDateTime stored in the task, if any.
     * The LocalDateTime is to be used for comparison with other tasks.
     *
     * @return LocalDateTime for comparison.
     */
    protected LocalDateTime getComparisonTime() {
        return null;
    }

    @Override
    public int compareTo(Task task) {

        if (this.getComparisonTime() == null && task.getComparisonTime() == null) {
            return 0;
        } else if (this.getComparisonTime() == null) {
            return -1;
        } else if (task.getComparisonTime() == null) {
            return 1;
        } else {
            return this.getComparisonTime().compareTo(task.getComparisonTime());
        }


    }


    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }


}
