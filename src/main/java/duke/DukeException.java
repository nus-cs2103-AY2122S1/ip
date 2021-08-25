package duke;

/**
 * DukeException class that extends from Exception class
 */
public class DukeException extends Exception{
    /**
     *
     * @param msg takes in a String representing the exception
     */
    public DukeException(String msg){
        super(msg);
    }
}
