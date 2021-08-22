public class Deadline extends Task {
    private String Dtime;

    public Deadline(String description, String s) {
        super(description);
        this.Dtime = s;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by:" + this.Dtime + ")";
    }

    @Override
    public String toStringConvert(){
        if(this.isCompleted()) {
            return "D | 1 | " +  this.getString() + "|" + this.Dtime;
        } else {
            return "D | 0 | " +  this.getString() + "|" + this.Dtime;
        }
    }
}
