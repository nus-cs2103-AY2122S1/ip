package duke.Tasks;

import duke.Tool.Storage;
import duke.Tool.TaskList;
import duke.Ui.Ui;


/**
 * Represents the Task class
 */
public class Task {
    protected String description;
    protected boolean isDone;
    public boolean isExit;

    /**
     * Constructs Task class
     *
     * @param description
     * @param isDone
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.isExit = false;
    }

    /**
     * Gets the status icon
     *
     * @return String of status of icon
     */
    public String getStatusIcon() {
        return (this.isDone ? "[X] " : "[ ] ");
    }

    /**
     * Marks down the task
     *
     * @return Task object
     */
    public Task markDone() {
        this.isDone = true;
        assert isDone: "Mark unsuccessful";
        return this;

    }

    /**
     * Gets the description of task
     *
     * @return String the description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Changes the format let "X" -> 1 and record to tasks.txt version
     *
     * @return String changed format of  task in record
     */
    public String changeFormat() {
        String mark = isDone ? "1" : "0";
        return "|" + mark + "0" + this.description;

    }

    /**
     * Overrides toString method to task details
     *
     * @return String of task details
     */
    @Override
    public String toString() {
        return this.getStatusIcon()  + this.description;
    }

    /**
     * Executes input task
     *
     * @param tasks
     * @param ui
     * @param storage
     * @return String null
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return "Invalid input, please enter again\n" +
                "E.g. list, deadline, find...";
    };

}
