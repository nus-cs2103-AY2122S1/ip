package duke.tasks;

/**
 * Class that encapsulates a task and its information
 */
public class Task {

    protected TaskType type;
    protected String name;
    protected boolean isDone = false;

    public enum TaskType {
        TODO, DEADLINE, EVENT
    }

    /**
     * Constructor that initializes a Task object
     * @param name name of task
     * @param type type of task (TODO, DEADLINE, EVENT)
     */
    public Task(String name, TaskType type) {
        this.name = name;
        this.type = type;
    }

    /**
     * Method that returns whether or not task is done
     *
     * @return boolean whether or not task is done
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Method that returns the task type of the task
     *
     * @return TaskType task's type
     */
    public TaskType getType() {
        return type;
    }

    /**
     * Method that returns the name of the task
     *
     * @return String name of task
     */
    public String getName() {
        return name;
    }

    /**
     * Method that marks the task as done by
     * changing the Task's boolean variable isDone
     * to true.
     */
    public void makeDone() {
        isDone = true;
    }

    @Override
    public String toString() {
        String typeString = type == TaskType.TODO ? "T" : type == TaskType.EVENT ? "E" : "D";
        String doneSymbol = isDone ? "X" : " ";
        String result = "[" + typeString + "] " + "[" + doneSymbol + "] " + name;
        return result;
    }

}
