public class Task {
    private String description;
    private boolean done;

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
