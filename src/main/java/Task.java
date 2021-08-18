public class Task {
    protected String title;
    protected boolean done;
    protected char typeIndicator;

    protected enum TypeIndicators {
        TODO, EVENT, DEADLINE
    }
    private static final char TYPE_INDICATOR_TODO = 'T';
    private static final char TYPE_INDICATOR_EVENT = 'E';
    private static final char TYPE_INDICATOR_DEADLINE = 'D';

    /**
     * A constructor for a Task.
     *
     * @param title a String representing the user-input title of the task.
     */
    public Task(String title, TypeIndicators taskType) {
        this.title = title;
        this.done = false;
        switch (taskType) {
            case TODO:
                this.typeIndicator = TYPE_INDICATOR_TODO;
                break;
            case EVENT:
                this.typeIndicator = TYPE_INDICATOR_EVENT;
                break;
            case DEADLINE:
                this.typeIndicator = TYPE_INDICATOR_DEADLINE;
                break;
        }
    }

    /**
     * Marks a task as done.
     */
    public void completeTask() {
        this.done = true;
    }

    /**
     * Returns the string representation of a task.
     *
     * @return A string describing the task.
     */
    @Override
    public String toString() {
        String doneIndicator = this.done ? "x" : " ";
        return String.format("[%s][%s] %s", typeIndicator, doneIndicator, this.title);
    }
}
