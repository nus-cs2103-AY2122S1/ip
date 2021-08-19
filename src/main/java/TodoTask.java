package main.java;

/**
 * Task subclass that encapsulates the individual "Todo" task passed into the bot.
 */
public class TodoTask extends Task {

    /**
     * Constructor for the Todo class
     */
    TodoTask(String task) {
        super(task);
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
}