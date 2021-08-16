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

    @Override
    public String toString() {
        String checkBox = done
                ? "[X] "
                : "[ ] ";
        return checkBox + description;
    }
}
