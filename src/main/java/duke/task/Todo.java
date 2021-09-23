package duke.task;

/**
 * @author Dr-Octavius
 *
 * Class that represents a 'todo' Task.
 * Child Class of Task Class
 *
 * @version 1.0
 */
public class Todo extends Task {

    /**
     * Class Constructor that takes 2 parameter
     *
     * @param description Task description
     */
    public Todo(boolean state, String description) {
        super(TASK_TYPE.T,description,state);
    }

    /**
     * Class Constructor that takes 1 parameter
     *
     * @param description Task description
     */
    public Todo(String description) {
        super(TASK_TYPE.T,description);
    }

}