/**
 * Represents a ToDo object that can be added
 * to users' task list.
 *
 * @author Ne Zhijian, Didymus A0218159Y
 */
public class ToDo extends Task {

    protected ToDo(String s) {
        super(s);
    }

    /**
     * String representation of the ToDo object
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
