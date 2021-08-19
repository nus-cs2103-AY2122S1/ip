public class Task {
    protected String title;
    protected boolean done;
    protected char typeIndicator;

    protected enum TypeIndicators {
        TODO('T'),
        EVENT('E'),
        DEADLINE('D');

        public final char indicator;

        private TypeIndicators(char indicator) {
            this.indicator = indicator;
        }

        public void setIndicatorForTask(Task task) {
            task.typeIndicator = this.indicator;
        }
    }

    /**
     * A constructor for a Task.
     *
     * @param title a String representing the user-input title of the task.
     */
    public Task(String title, TypeIndicators taskType) {
        this.title = title;
        this.done = false;
        taskType.setIndicatorForTask(this);
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
