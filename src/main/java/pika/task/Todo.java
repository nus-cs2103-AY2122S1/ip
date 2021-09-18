package pika.task;

/**
 * Todo Task to handle todo Tasks.
 */
public class Todo extends Task {

    /**
     * Constructor class for todo Task.
     *
     * @param name The name of the task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Returns the string to be written in the txt file.
     *
     * @return txt format of the task for storage.
     */
    @Override
    public String write() {
        String tags = " |" + getTags();
        if (tags.equals(" |")) {
            return "T " + super.write();
        } else {
            return "T " + super.write() + " |" + getTags();
        }
    }

    /**
     * Returns the list format of the task.
     *
     * @return list format of the task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
