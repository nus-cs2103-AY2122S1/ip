public class Events extends Task {
    protected final String dayTime;

    public Events(String description, String dayTime) {
        super(description);
        this.dayTime = dayTime;
    }

    public Events(String description, String dayTime, boolean isDone) {
        super(description, isDone);
        this.dayTime = dayTime;
    }

    public String getDayTime() {
        return this.dayTime;
    }

    @Override
    public Events markAsDone() {
        return new Events(this.getDescription(), this.getDayTime(), true);
    }

    @Override
    public String toString() {
        return "[E][" + this.getStatusIcon() + "] " + this.getDescription() + "(at:" + this.getDayTime() + ")";
    }
}
