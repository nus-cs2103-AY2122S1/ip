public class Deadline extends Task{
    private String deadline;
    private final static String symbol = "[D]";

    public Deadline(String action, String deadline){
        super(action);
        this.deadline = deadline;
    }

    public String toString(){
        return String.format("%s%s (by: %s)", symbol, super.toString(), deadline);
    }
}
