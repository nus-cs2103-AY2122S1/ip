public class Deadline extends Task{

    private String time;

    private Deadline(String description, String time) {
        super(description);
        this.time = time;
    }

    public static Deadline of(String args) {
        // parse args
        String[] parsedArgs = args.split(" /by ");
        return new Deadline(parsedArgs[0], parsedArgs[1]);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.time);
    }
}
