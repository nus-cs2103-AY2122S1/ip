import java.util.Optional;

public class ToDo extends Task{

    private ToDo(String description) {
        super(description, "T");
    }

    public static ToDo create(Optional<String> description) throws DukeExceptions{
        String desc = description.orElseThrow(() -> new DukeExceptions(
                "Oops, todo command requires a description \n"
        ));
        return new ToDo(desc);
    }
}
