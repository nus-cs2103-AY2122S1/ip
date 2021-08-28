package main.java;

/**
 * Task subclass that encapsulates the individual "Todo" task passed into the bot.
 */
public class TodoTask extends Task {

    /**
     * Constructor for the Todo class
     * Only takes in the string
     */
    TodoTask(String task) {
        super(task);
    }

    /**
     * Constructor for the Todo class
     */
    TodoTask(String task, boolean done) {
        super(task, done);
    }

    /**
     * Retrieves the completion state of the task, followed by the task input.
     *
     * @return The String representation of the task completion state and the task input.
     */
    @Override
    public String getTaskState() {
        return "[T]" + super.getTaskState();
    }

    @Override
    public String convertToStorageFormat() {
        return "T,"
                + (done ? "1," : "0,")
                + task;
    }
}