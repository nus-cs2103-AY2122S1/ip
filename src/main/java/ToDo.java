public class ToDo extends Task{
    protected String logo = "[T]";

    public ToDo(String description) {
        super(description);
    }

    public String logo() {
        return logo;
    }

    @Override
    public String toString() {
        return "[T]" + super.getStatusIcon() +  " " + super.description;
    }
}

