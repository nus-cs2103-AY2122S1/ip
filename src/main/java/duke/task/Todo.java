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
     * Class Constructor
     *
     * @param description Task description
     */
    public Todo(String description) {
        super(description,TASK_TYPE.T);
    }

}