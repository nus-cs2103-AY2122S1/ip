package whobot.task;

/***
 * Class to Handle Todo type Tasks
 */
public class Todo extends Task {

    /***
     * Constructor for Todo Class
     *
     * @param task the string description of the Task
     */
    public Todo(String task) {
        super(task);
    }

    /***
     * Returns the String description of the task
     *
     * @return description of the task
     */
    @Override
    public String getDescription() {
        return "[T] " + super.getDescription();
    }

    /***
     * Returns the Type of Task
     *
     * @return T since Todo Type
     */
    @Override
    public String getType() {
        return "T";
    }

    /***
     * Returns string representation of the task
     *
     * @return string to display for the task
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    /***
     * Compares this task to another, to help with sorting
     *
     * @param o Task to compare to
     * @return result after comparing the description of the task
     */
    @Override
    public int compareTo(Task o) {
        int val = super.compareTo(o);
        if (val == 0) {
            if (o instanceof Deadline || o instanceof Event) {
                return 1;
            } else {
                return this.getDescription().compareTo(o.getDescription());
            }
        } else {
            return val;
        }
    }

    /***
     * Equates this task to another
     *
     * @param obj Task to equate to
     * @return true if both have the same description and are done
     */
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
