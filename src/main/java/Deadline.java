public class Deadline extends Task{
    String date;
    public Deadline(String desc, String date) {
        super(desc);
        this.date= date;
    }

    public Deadline(String isDone, String desc, String date) {
        super(isDone,desc);
        this.date= date;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by: "+date+")";
    }

    @Override
    public String saveString() {
        return "D|" + super.saveString() + "|" + date;
    }
}
