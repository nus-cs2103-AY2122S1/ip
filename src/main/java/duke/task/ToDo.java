package duke.task;

public class ToDo extends Task {

    /**
     * This is a constructor for the ToDo object.
     *
     * @param description The description of the ToDo
     */
    public ToDo(String description) {
        super(description);
        this.taskType = "T";
    }

    @Override
    public boolean equals(Object object) {
        ToDo otherTodo = (ToDo) object;
        return this.getStatusIcon().equals(otherTodo.getStatusIcon())
                && this.description.equals(otherTodo.description)
                && this.taskType.equals(otherTodo.taskType);
    }

    public String getDataString() {
        return super.getDataString();
    }
}
