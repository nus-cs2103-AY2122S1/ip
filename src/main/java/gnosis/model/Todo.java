package gnosis.model;

import java.time.LocalDateTime;

/**
 *
 * This class encapsulates a todo gnosis.task.
 *
 * @author Pawandeep Singh
 * @version Level-4
 * */
public class Todo extends Task {
    public Todo(String task) {
        super(task, TaskType.TODO);
    }

    public Todo(String task, Boolean isTaskDone) {
        super(task, TaskType.TODO, LocalDateTime.now(), isTaskDone);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
