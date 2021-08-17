public class Deadline extends Task{
    String date;
    public Deadline(String desc, String date) {
        super(desc);
        this.date= date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "+date+")";
    }
}
