package duke.task;

public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
    }

    public ToDo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String populateSaveData() {
        return "T | " + (this.isDone ? 1 : 0) + " | " + this.description;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) return true;

        if (o != null && o.getClass() == this.getClass()) {
            return ((ToDo) o).isDone == this.isDone
                    && ((ToDo) o).description.equals(this.description);
        }
        return false;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
