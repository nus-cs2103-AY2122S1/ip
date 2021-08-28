package main.java.task;

/**
 * Task subclass that encapsulates the individual "Todo" main.java.task passed into the main.java.bot.
 */
public class TodoTask extends Task {

    /**
     * Constructor for the Todo class
     * Only takes in the string
     */
    public TodoTask(String task) {
        super(task);
    }

    /**
     * Constructor for the Todo class
     */
    public TodoTask(String task, boolean state) {
        super(task, state);
    }

    /**
     * Retrieves the completion state of the main.java.task, followed by the main.java.task input.
     *
     * @return The String representation of the main.java.task completion state and the main.java.task input.
     */
    @Override
    public String getTaskState() {
        return "[T]" + super.getTaskState();
    }

    @Override
    public String convertToStorageFormat() {
        return "T,"
                + (state ? "1," : "0,")
                + task;
    }
}