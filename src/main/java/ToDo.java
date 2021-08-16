public class ToDo extends Task{
    protected String description;
    protected boolean isDone;
    final String TODO = "[T]";

    public ToDo(String description) {
        super(description);
        this.description = description;
        this.isDone = false;
    }

    @Override
    public String getStatusAndDescription() {return TODO + this.getStatusIcon() + " " + this.getDescription(); }


}
