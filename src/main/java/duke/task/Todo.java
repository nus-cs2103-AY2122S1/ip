package duke.task;

public class Todo extends Task{

    /**
     * Constructor for Todo.
     *
     * @param taskName the name of the input task
     * @param isDone the current status of the task
     */
    public Todo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    /**
     * Prints the task in specified format
     *
     * @return the String that has been formatted
     */
    public String displayTask() {
        return "T " + super.displayTask();
    }
}
