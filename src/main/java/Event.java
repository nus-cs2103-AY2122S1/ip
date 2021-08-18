public class Event extends Task {

    public Event(String description) {
        super(description);
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
        return this.getStatusIcon() + " " + this.getDescription();
    }
}
