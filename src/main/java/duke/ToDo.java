package duke;

public class ToDo extends Task{
    protected static final String TODO_LABEL = "T";

    public ToDo(String str) {
        super(str);
    }

    protected ToDo(String isDoneStr, String descriptionStr) {
        super(descriptionStr);
        this.isDone = Boolean.valueOf(isDoneStr);
    }

    @Override
    protected String getTaskType() {
        return TODO_LABEL;
    }

    @Override
    public String toString() {
        return "[" + TODO_LABEL + "]" + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ToDo) {
            ToDo todo = (ToDo) obj;
            return todo.description.equals(this.description);
        }
        return false;
    }
}
