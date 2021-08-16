/** Represents a task without any time restrictions
 *  @auther mokdarren
 */
public class ToDo extends Task{

    /**
     *
     * @param description description of task
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}