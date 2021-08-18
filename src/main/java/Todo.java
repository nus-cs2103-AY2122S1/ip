package main.java;

public class Todo extends Task {

    /**
     * Constructor for the Todo class
     */
    Todo(String task) {
        super(task);
    }

    /**
     * Retrieves the completion state of the task, followed by the task input.
     *
     * @return The String representation of the task completion state and the task input.
     */
    @Override
    public String getTaskState() {
        return "[T]" + (this.done ? "[X] " + this.task : "[ ] " + this.task);
    }
}
