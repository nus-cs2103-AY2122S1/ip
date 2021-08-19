public class Event extends Task{

    protected String time;

    Event(String description) {
        super(description);
        this.time = "";
    }

    Event(String description, String time) {
        super(description);
        this.time = time;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public String typeOfTask() {
        return "E";
    }

    @Override
    public String toString() {
        return String.format("[%s][%s] %s (at: %s)", this.typeOfTask(),
                this.getStatusIcon(), this.getDescription(), this.getTime());
    }
}
