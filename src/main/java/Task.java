/**
 * The is the Task class that contains the properties
 * of a task.
 *
 * @author  HU JIAJUN
 * @version %I%, %G%
 * @since   1.0
 */

public class Task {
    private final String name;

    /**
     * This is constructor method of Task.
     *
     * @param name task name
     */
    Task(String name) {
        this.name = name;
    }

    /**
     * Get name of task.
     *
     * @return task name
     */
    public String getName() {
        return name;
    }
}
