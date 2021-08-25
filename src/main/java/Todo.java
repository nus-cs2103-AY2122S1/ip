import java.io.IOException;

public class Todo extends Task {

    private static final String label = "T";

    public Todo(String description) throws DukeException, IOException {
        super(description);
    }

    @Override
    public String toString() {
        return "[" + label + "]" + super.toString();
    }

    @Override
    public String toDataString() {
        return label + super.toDataString();
    }
}




