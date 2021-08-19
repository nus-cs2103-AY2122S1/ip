/**
 * ToDo class IS-A Task.
 *
 * Task without any date/time attached to it
 * @author Timothy Wong Eu-Jin
 * @version Level-4
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public String toString() {
        return ("[T]" + super.toString());
    }
}
