package duke.tasks;

public class Todo extends Task {

    /**
     * Instantiates a blank Todo.
     */
    public Todo(String desc) {
        super(desc);
    }

    /**
     * Instantiates a Todo based on a database entry.
     */
    public Todo(String entry, boolean done) {
        super(entry, done);
    }

    void addTime(String rawArgs) {
    }

    /**
     * Returns this todo formatted as a database entry.
     *
     * @return String representing this event in database format.
     */
    public String toDatabaseFormat() {
        return String.format("T | %d | %s ", super.isDone ? 1 : 0, super.desc);
    }

    @Override
    public String toString() {
        return "[T]" + (isDone ? "[X] " : "[ ] ") + desc;
    }
}
