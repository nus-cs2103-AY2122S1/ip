public class Deadline extends Task {
    private String time;
    private String type = "D";

    public Deadline(String name, boolean done, String time) {
        super(name, done);
        this.time = time;
    }

    @Override
    public String log_record() {
        int state;
        if (this.done) {
            state = 1;
        } else {
            state = 0;
        }
        return "D , " + state + " , " + this.name + " , " + this.time;
    }


    @Override
    public String toString() {
        return "[D][" + this.getStatus() + "] " + this.name
                + "(by: " + this.time + ")";
    }
}
