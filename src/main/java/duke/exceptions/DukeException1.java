package duke.exceptions;

import java.lang.Exception;
public class DukeException1 extends Exception {
    DukeException1() {
        super();
    }

    @Override
    public String getMessage() {
        return "Error occurred!";
    }

    /**@Override
    public String toString() {
        return "OOPS!!!";
    }*/
}
