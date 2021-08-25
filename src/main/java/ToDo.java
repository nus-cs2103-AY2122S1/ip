public class ToDo extends Task{
    protected static final String TODO_LABEL = "T";

    public ToDo(String str) {
        super(str);
    }

    @Override
    protected String getTaskType() {
        return TODO_LABEL;
    }

    @Override
    public String toString() {
        return "[" + TODO_LABEL + "]" + super.toString();
    }
}
