package task;


public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getDescription() {
        return this.description;
    }


    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}