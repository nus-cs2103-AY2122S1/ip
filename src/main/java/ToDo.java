public class ToDo extends Task {

    protected String status;

    public ToDo(String name) {
        super(name);
        this.status = "[T]" + super.status;
    }

}
