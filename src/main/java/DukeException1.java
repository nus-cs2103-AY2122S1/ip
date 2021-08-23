import java.lang.Exception;
public class DukeException1 extends Exception {
    DukeException1() {
        super();
    }

    @Override
    public String toString() {
        return "OOPS!!!";
    }
}
