package Duke.Tasks;

import Duke.Tool.Storage;
import Duke.Tool.TaskList;
import Duke.Ui;


/**
 * Represents the Toddo task class
 */
public class Todo extends Task {

    /**
     * The constructor for Todo task
     * @param description
     * @param isDone
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * the markDone method for marking the Todo task as done
     * @return Todo object
     */
    @Override
    public Todo markDone() {
        super.markDone();
        return this;
    }

    /**
     * The formatChange method for change the done x -> 1 in Writing record tasks.txt version
     * @return String
     */
    @Override
    public String formatChange() {
        String mark = isDone ? "1" : "0";
        return "T | " + mark + " | " + this.description;
    }

    /**
     *
     * @param task
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList task, Ui ui, Storage storage)  {
        task.add(this);
        ui.showAddOnTask(task, (task.size() - 1));
        storage.writeData(task.getTasks());
    }

    /**
     * Overridden toString method to print Todo task details
     * @return String
     */
    @Override
    public String toString() {
        return "[T]"  + super.toString();
    }
}
