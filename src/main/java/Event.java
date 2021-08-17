public class Event extends Task {
    private String date;

    public Event(String title, String date) {
        super(title);
        this.date = date;
    }

    @Override
    public String getInfo() {
        return "[E][ ]" + this.getTitle() + "(at: " + this.date + ")";
    }

    @Override
    public String toString() {
        if (!this.getDone()) {
            return String.format("[E][ ]" + this.getTitle() + "(at: " + this.date + ")");
        } else {
            return String.format("[E][X]" + this.getTitle() + "(at: " + this.date + ")");
        }

    }


}
