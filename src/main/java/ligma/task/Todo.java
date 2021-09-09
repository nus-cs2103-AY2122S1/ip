package ligma.task;

/**
 * This class represents a Todo Task.
 */
public class Todo extends Task {
    /**
     * Creates a Todo object.
     *
     * @param desc                      the description of the todo
     * @return                          todo created
     */
    public Todo(String desc) {
        super(desc, desc);
    }

    /**
     * Creates a Todo object.
     *
     * @param desc                      the description of the todo
     * @param isDone                    whether task has been completed
     * @return                          todo created
     */
    public Todo(String desc, boolean isDone) {
        super(desc, isDone, desc);
    }

    @Override
    public String getFullMeta() {
        return "T" + super.getFullMeta();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Todo) {
            return super.equals(obj);
        }
        return false;
    }
}
