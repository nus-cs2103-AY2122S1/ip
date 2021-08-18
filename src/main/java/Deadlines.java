public class Deadlines extends Task {

    private String deadline;

    public Deadlines(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        if (isDone) {
            return ("[D] [X] " + name + " (by: " + deadline + ")");
        } else {
            return ("[D] [ ] " + name + " (by: " + deadline + ")");
        }
    }
}
