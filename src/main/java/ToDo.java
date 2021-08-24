/**
 * To do class IS-A Task.
 *
 * Task without any date/time attached to it
 * @author Timothy Wong Eu-Jin
 * @version Level-8
 */
public class ToDo extends Task {

    //Constructor
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return ("[T]" + super.toString());
    }
}
