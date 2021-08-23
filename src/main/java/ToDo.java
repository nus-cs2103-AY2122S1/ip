import java.util.Objects;

public class ToDo extends Task{

    public ToDo(String description) {
        super(description.trim());
    }

    public ToDo(String description, Boolean empty) throws DukeException {
        super(description.trim());

        if (empty || description.trim().isEmpty()) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
