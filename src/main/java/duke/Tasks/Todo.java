package duke.Tasks;

import duke.Tool.Storage;
import duke.Tool.TaskList;
import duke.Ui.Ui;


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
     * Changes the format let "X" -> 1 and record to tasks.txt version
     *
     * @return String
     */
    @Override
    public String changeFormat() {
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
    public String execute(TaskList task, Ui ui, Storage storage)  {
        int numOfBeforeExecute = task.size();
        task.add(this);
        assert task.size() - numOfBeforeExecute == 1 : "Add todo task not successful";
        storage.writeData(task.getTasks());
        return ui.showAddOnTask(task, (task.size() - 1));
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
