public class Event extends Task {
    private boolean isDone;

    public Event(String description) {
        super(description);
        this.isDone = false;
    }

    @Override
    public String getDescription() {
        String temp = super.getDescription();
        temp = temp.replace("event ", "");
        temp = temp.replace("/at", "(at:");
        return temp + ")";
    }

    @Override
    public void markedDone() {
        super.markedDone();
    }

    @Override
    public String getStatusIcon() {
        return "[E]" + super.getStatusIcon();
    }

    @Override
    public String printTask() {
        return super.printTask();
    }
}
