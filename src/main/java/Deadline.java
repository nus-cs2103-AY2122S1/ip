public class Deadline extends Task {
    private boolean isDone;

    public Deadline(String description) {
        super(description);
        this.isDone = false;
    }

    @Override
    public String getDescription() {
        String temp = super.getDescription();
        temp = temp.replace("deadline ", "");
        temp = temp.replace("/by", "(by:");
        return temp + ")";
    }

    @Override
    public void markedDone() {
        super.markedDone();
    }

    @Override
    public String getStatusIcon() {
        return "[D]" + super.getStatusIcon();
    }

    @Override
    public String printTask() {
        return this.getStatusIcon() + super.printTask();
    }
}
