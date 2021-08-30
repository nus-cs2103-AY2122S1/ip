package duke.exceptions;

/**
 * Class of exception that handles marking task
 * number greater than number of task available as done
 */
public class DoneOutOfBoundsException extends DukeException {
    
    /**
     * Instantiates a done exception
     * 
     * @param taskLength Integer of the length of the tasklist
     */
    public DoneOutOfBoundsException(int taskLength) {
        super("You only have " + taskLength + " tasks! Cannot mark as Done!");
    }
}
