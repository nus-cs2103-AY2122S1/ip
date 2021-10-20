package jackie.task;

import jackie.JackieException;

/**
 * A Class that extends the Task class.
 * It is specifically designed for a Task with time of occurrence.
 *
 * @author Gu Geng
 */
public class Todo extends Task {
    /**
     * Returns a Todo object using a String.
     *
     * @param content A String containing information that is possibly enough to create a Todo object.
     * @throws jackie.JackieException Will be thrown if the information in content is insufficient/incorrect.
     */
    public Todo(String... content) {
        super(content);
    }

    /**
     * Overrides the toString method.
     *
     * @return A String representation of the Todo object in specified format.
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s",
                getStatus() ? "X" : " ", getContent());
    }

    /**
     * Implements the record abstract method from Task.
     *
     * @return A String of the Todo object in specified format for starage purpose.
     */
    public String record() {
        return String.format("T | %s | %s",
                getStatus() ? "1" : "0", getContent());
    }

    /**
     * Implements the getType method from Task.
     *
     * @return A String indicating the type of Todo as a Task.
     */
    public String getType() {
        return "T";
    }

    /**
     * Returns the time factor of the Task in String form where applicable.
     *
     * @return A String indicating the time factor of the Task.
     */
    public String getTime() {
        return "null";
    }

    @Override
    public <T extends Task> T getCopy() throws JackieException {
        String inputHolder = "todo " + this.getContent();
        //noinspection unchecked
        return (T) new Todo(inputHolder.split(" "));
    }

    /**
     * Implements the hasSchedule method from Task.
     *
     * @return A boolean indicating if Todo has a schedule.
     */
    public boolean hasSchedule() {
        return false;
    }


}
