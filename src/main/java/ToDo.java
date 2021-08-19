import java.util.Optional;

public class ToDo extends Task{

    private ToDo(String description) {
        super(description, "T");
    }

    public static ToDo create(String description) throws DukeExceptions{
        return new ToDo(description);
    }
}
