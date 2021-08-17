public class Task {
    private String title;

    public Task(String title) {
        this.title = title;
    }

    /**
     * Returns the string representation of a task.
     *
     * @return A string describing the task.
     */
    @Override
    public String toString() {
        return this.title;
    }
}
