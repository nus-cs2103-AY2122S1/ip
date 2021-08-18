/**
 * Represents a Task object that can be added
 * to users' task list.
 * A Task can be a ToDo, Deadline or Event.
 *
 * @author Ne Zhijian, Didymus A0218159Y
 */
public class Task {
    protected boolean isDone;
    protected String task;

    protected Task(String task) {
        this.isDone = false;
        this.task = task;
    }

    /**
     * Creates a new Task object
     *
     * @param task String description of the task
     */
    public static Task createTask(String task) throws IllegalArgumentException  {
        String[] taskArr = task.split(" ", 2);
        String firstWord = taskArr[0];
        if (taskArr.length < 2) {
            throw new IllegalArgumentException("OOPS! The task cannot be empty");
        } else if (firstWord.equals("todo")) {
            return new ToDo(taskArr[1]);
        } else if (firstWord.equals("deadline")) {
            return new Deadline(taskArr[1].split("/", 2));
        } else if (firstWord.equals("event")) {
            return new Event(taskArr[1].split("/", 2));
        } else {
            throw new IllegalArgumentException("OOPS! This is not a task. ");
        }

    }

    /**
     * Sets the task to be done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * String representation of the Task object
     */
    @Override
    public String toString() {
        String checker = this.isDone ? "X" : " ";
        return "[" + checker + "]" + " " + this.task;
    }
}
