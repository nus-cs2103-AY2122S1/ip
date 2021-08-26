package task;

import java.time.LocalDate;

/**
 * Represents a general task to be done. Does not have a deadline or event period.
 */
public class Todo extends Task {

    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Returns this Todo's list entry string.
     *
     * @return A string representation of this Todo's list entry.
     */
    @Override
    public String listEntry() {
        return "[T]" + super.listEntry();
    }

    /**
     * Returns this Todo's database entry string.
     *
     * @return A string representation of this Todo's database entry.
     */
    @Override
    public String databaseEntry() {
        return "T" + super.databaseEntry();
    }

    /**
     * Returns false since Todo objects do not have date/time attributes.
     * 
     * @param l The date against which to check this Todo object.
     * @return False.
     */
    @Override
    public boolean isTodayTask(LocalDate l) {
        return false;
    }
}
