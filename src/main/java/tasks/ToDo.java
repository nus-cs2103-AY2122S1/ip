package tasks;

public class ToDo extends Task{
    public ToDo(String description) {
        super(description, "[T]", false);
    }

    public ToDo(String description, boolean status) {
        super(description, "[T]", status);
    }

    public String getFormattedData() {
        String formattedStatus = super.isDone() ? "1|" : "0|";
        return "T|" + formattedStatus + super.getDescription();
    }
}
