/**
 * a class to encapsulate exceptions raised in Duke.
 */
public class DukeException extends Exception{
    private String message;
    private Exception e;
    public DukeException(Exception e) {
        this.e = e;
    }

    /**
     * method that returns the exception message.
     * @return exception message.
     */
    
    public String print_message() {
        if (e instanceof ArrayIndexOutOfBoundsException) {
            message = "☹ OOPS!!! The task does not exist.";
        } else if (e instanceof StringIndexOutOfBoundsException) {
            message = "☹ OOPS!!! The description of a todo cannot be empty.";
        } else {
            message = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
        return message;
    }
}
