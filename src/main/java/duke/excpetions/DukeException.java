/**
 * @author Hang Zelin
 *
 * @description This is an exception class which deals with invalid input or errors.
 *
 */

package duke.excpetions;

public class DukeException extends Exception {

    private String ErrorMessage = "";

    /**
     * @author Hang Zelin
     *
     * @description Constructor that stores the ErrorMessage encountered in Duke programme.
     *
     * @param ErrorMessage
     */
    public DukeException(String ErrorMessage){
        this.ErrorMessage = ErrorMessage;
    }

    /**
     * @author Hang Zelin
     *
     * @description Print out the Error Message in DukeException.
     *
     * @param
     * @return void
     */
    public void PrintErrorMessage(){
        System.out.println(this.ErrorMessage);
    }

    /**
     * @author Hang Zelin
     *
     * @description Return the Error Message in DukeException.
     *
     * @param
     * @return String
     */
    @Override
    public String getMessage() {
        return this.ErrorMessage;
    }
}
