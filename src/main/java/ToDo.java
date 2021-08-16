import java.util.Optional;

public class ToDo extends Task{

    private ToDo(String description) {
        super(description);
    }

    public static ToDo of(Optional<String> description) throws IllegalArgumentException {
        return new ToDo(description.orElseThrow(() -> new IllegalArgumentException(
                "☹ OOPS!!! The description of a todo cannot be empty."
        )));
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
