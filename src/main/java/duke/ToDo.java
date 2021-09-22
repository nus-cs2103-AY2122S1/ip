package duke;

public class ToDo extends Task {
    public static final String TODO_NAME = "todo";
    public static final String TODO_INITIAL = "T";

    public ToDo(String name){
        super(name, false);
    }

    public ToDo(String name, boolean isDone){
        super(name, isDone);
    }

    @Override
    public String toCsvRow() {
        return String.join(",", TODO_NAME, name, String.valueOf(isDone));
    }

    @Override
    public String toString(){
        return String.format("[%s][%s] %s", TODO_INITIAL, isDone ? "X" : " ", this.getName());
    }
}
