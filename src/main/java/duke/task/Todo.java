package duke.task;

public class Todo extends Task {

    /**
     * Constructor for the Task
     * @param description Description of the Task
     * @param done Whether the Task is done
     */
    public Todo(String description, boolean done) {
        super(description, done);
    }

    /**
     * @return String of the task data for saving in tasks.txt
     */
    @Override
    public String toFileData() {
        return String.join(Task.STORAGE_DELIMITER, Task.TODO_ALPHABET, super.toFileData());
    }

    /**
     * @return String representation of the Task
     */
    @Override
    public String toString() {
        return super.wrapTaskAlphabet(Task.TODO_ALPHABET) + super.toString();
    }
}
