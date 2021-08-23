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

    public String[] saveStrings() {
        String isDone = this.getIsDone() ? "1" : "0";
        return new String[] {"T", isDone, this.getDescription()};
    }
}
