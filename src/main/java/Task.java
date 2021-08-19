public abstract class Task {
    protected String description;
    protected boolean done;

    public Task(String input) {
        description = input;
        done = false;
    }

    public boolean toggleDone() {
        done = !done;
        return done;
    }

    /**
     * String representation of Task
     *
     * @return task display
     */
    @Override
    public String toString() {
        String checkBox = done
                ? "[X] "
                : "[ ] ";
        return checkBox + description;
    }

    abstract String saveString();
}
