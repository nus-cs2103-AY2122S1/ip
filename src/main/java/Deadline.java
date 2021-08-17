public class Deadline extends Task{

    private String deadLine;

    public Deadline(String task, String deadLine) {
        super(task);
        this.deadLine = deadLine;
    }

    @Override
    String printTask() {
        String result = "";
        if (super.complete) {
            result = "[D][X] ";
        } else {
            result = "[D][ ] ";
        }
        return result + super.task + "(by: " + this.deadLine + ")";
    }
}
