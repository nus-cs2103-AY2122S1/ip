/**
 *
 * This class encapsulates a todo task.
 *
 * @author Pawandeep Singh
 * @version Level-4
 * */
public class Todo extends Task {
    public Todo(String task) {
        super(task,TaskType.TODO);
    }

    public Todo(String task, Boolean isTaskDone) {
        super(task, TaskType.TODO, "", isTaskDone);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
