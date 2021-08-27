package Duke.Tasks;

import Duke.Tool.Storage;
import Duke.Tool.TaskList;
import Duke.Ui;


/**
 * Represents the Task class
 */
public class Task {
    protected String description;
    protected boolean isDone;
    public boolean isExit;

    /**
     * The constructor for task
     * @param description
     * @param isDone
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.isExit = false;
    }

    /**
     * The method of getStatus
     * @return
     */
    public String getStatusIcon() {
        return (this.isDone ? "[X] " : "[ ] ");
    }

    /**
     * The method of markDone, change status to done
     * @return Task object
     */
    public Task markDone() {
        this.isDone = true;
        return this;

    }

    /**
     * The formatChange method for change the done x -> 1 in Writing record tasks.txt version
     * @return String
     */
    public String formatChange() {
        String mark = isDone ? "1" : "0";
        return "|" + mark + "0" + this.description;

    }

    /**
     * Overridden toString method to task details
     * @return String
     */
    @Override
    public String toString() {
        return this.getStatusIcon()  + this.description;
    }

    /**
     * Executes input task
     * @param task
     * @param ui
     * @param storage
     */
    public void execute(TaskList task, Ui ui, Storage storage) {

    };

}
