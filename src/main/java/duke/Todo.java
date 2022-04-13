package duke;

/**
 * simplest kind of task
 *
 * @author Chen Yanyu
 */

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * return the save string arrays for file writing
     *
     * @return the array of Strings representing the task
     */
    public String[] getAsSaveStrings() {
        String isDone = this.getIsDone() ? "1" : "0";
        return new String[]{"T", isDone, this.getDescription()};
    }
}
