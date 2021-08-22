package duke.task;

/**
 * A Class that extends the Task class.
 * It is specifically designed for a Task with time of occurrence. 
 *
 * @author Gu Geng
 */
public class Todo extends Task{

    /**
     * Returns a Todo object using a String. 
     *
     * @param content A String containing information that is possibly enough to create a Todo object.
     * @throws duke.DukeException Will be thrown if the information in content is insufficient/incorrect. 
     */
    public Todo(String content) {
        super(content.substring(5).trim());
    }

    /**
     * Overrides the toString method.
     *
     * @return A String representation of the Todo object in specified format. 
     */
    @Override
    public String toString() {
        return String.format("[T][%s] %s",
                this.getStatus() ? "X" : " ", this.getContent());
    }

    /**
     * Implements the record abstract method from Task.
     *
     * @return A String of the Todo object in specified format for starage purpose.
     */
    public String record() {
        return String.format("T | %s | %s",
                this.getStatus() ? "1" : "0", this.getContent());
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
     * Implements the hasSchedule method from Task.
     *
     * @return A boolean indicating if Todo has a schedule.
     */
    public boolean hasSchedule() {
        return false;
    }


}
