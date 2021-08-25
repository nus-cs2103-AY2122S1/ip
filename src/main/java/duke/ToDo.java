package duke;

public class ToDo extends Task {
    protected String description;
    protected boolean isDone;
    final String TODO = "[T]";

    public ToDo(String description) {
        super(description);
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String toString() {
        return TODO + this.getStatusIcon() + " " + this.getDescription();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof ToDo) {
            ToDo toDo = (ToDo) object;
            return toDo.getDone() == this.getDone() && toDo.description.equals(this.description);
        }
        return false;
    }
}
