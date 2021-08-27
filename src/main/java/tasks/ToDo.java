package tasks;

public class ToDo extends Task{
    public ToDo(String description) {
        super(description, "[T]", false);
    }

    public ToDo(String description, boolean status) {
        super(description, "[T]", status);
    }

    public String getformmatedData() {
        String formmatedStatus = super.isDone() ? "1|" : "0|";
        return "T|" + formmatedStatus + super.getDescription();
    }
}
