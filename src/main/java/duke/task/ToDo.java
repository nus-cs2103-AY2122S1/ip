package duke.task;

public class ToDo extends Task{

    public ToDo(String name) {
        super(name, "T");
    }

    public ToDo(String name, boolean completed) {
        super(name, completed, "T");
    }

}
