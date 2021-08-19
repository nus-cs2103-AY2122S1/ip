/**
 * simplest kind of task
 *
 * @author Chen Yanyu
 */

class Todo extends Task {
    public Todo(String description) throws EmptyDescriptionException {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
