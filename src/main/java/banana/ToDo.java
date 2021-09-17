package banana;

/**
 * This class handles to-do
 * types of tasks.
 *
 * @author: Ravi Ananya
 */
class ToDo extends Task {

    /**
     * Constructor for ToDo.
     *
     * @param description user input.
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}

