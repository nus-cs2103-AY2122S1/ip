package duke.tasks;

public class ToDo extends Task{

    private ToDo(String description) {
        super(description, "T");
    }

    public static ToDo create(String description) {
        return new ToDo(description);
    }
}
