public class Task {
    private boolean done = false;
    private String name;

    public Task(String name) throws DukeException {
        if (name.isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a task cannot be empty.");
        }
        this.name = name;
    }

    public void Done() {
        this.done = true;
    }

    @Override
    public String toString() {
        if (done) {
            return "[X] " + name;
        } else {
            return "[ ] " + name;
        }
    }
}
