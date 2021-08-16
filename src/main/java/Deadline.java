public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by,int number) {
        super(description,number);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + by + ")" ;
    }
}
