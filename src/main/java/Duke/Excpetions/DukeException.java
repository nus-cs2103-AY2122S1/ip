/**
 * @author Hang Zelin
 *
 * Exception class which deals with invalid input or errors.
 */

package duke.excpetions;

public class DukeException extends Exception {

    private String errorMessage = "";

    /**
     * Constructor that stores the ErrorMessage encountered in Duke programme.
     *
     * @param errorMessage Detail info of the error.
     */
    public DukeException(String errorMessage){
        this.errorMessage = errorMessage;
    }

    /**
     * Prints out the Error Message in DukeException.
     */
    public void printErrorMessage(){
        System.out.println(this.errorMessage);
    }

    /**
     * Returns the Error Message in DukeException.
     *
     * @return Detailed info of error message.
     */
    @Override
    public String getMessage() {
        return this.errorMessage;
    }
}
