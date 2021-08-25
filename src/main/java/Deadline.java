public class Deadline extends Task {
    private String time;

    public Deadline(String rawTime) throws SkeltalException{
        super(rawTime.split("/", 2)[0]);
        String[] procTime = rawTime.split("/", 2);
        if (procTime.length == 1) {
            throw new SkeltalException("OOPS! The description of a deadline cannot be empty!");
        }
        String time = rawTime.split("/", 2)[1];
        this.time = time;
    }

    public String formatTime() {
        String formatTime = "(" + this.time + ")";
        return formatTime;
    }

    @Override
    public String store() {
        return "D | " + super.store() + "| " + this.time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + formatTime();
    }
}
