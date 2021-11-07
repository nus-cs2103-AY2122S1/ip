package duke.task;

/**
 * This is a ToDo class that extends Task.
 */
public class ToDo extends Task {

    /**
     * This is a ToDo Constructor.
     *
     * @param description A String representing the details of the ToDo task.
     */
    public ToDo(String description) {
        super(description);
        assert !description.equals("") : "ToDo \"description\" field cannot be empty!";
    }

    @Override
    public String fullCommand() {
        return "todo " + this.description;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public int compareTo(Task o) {
        if (this == o) {
            return 0;
        }
        if (!(o instanceof ToDo)) {
            return 1;
        } else { // compare lexicographically
            return this.description.compareTo(o.description);
        }
    }
}
