public class Event extends Task {
    private String date;

    public Event(String title, int indexNumber, String date) {
        super(title, indexNumber);
        this.date = date;
    }

    @Override
    public String getInfo() {
        return "[E][ ]" + this.getTitle() + "(at: " + this.date + ")";
    }

    @Override
    public String toString() {
        if (!this.getDone()) {
            return String.format(this.getIndexNumber() + ".[E][ ]" + this.getTitle() + "(at: " + this.date + ")");
        } else {
            return String.format(this.getIndexNumber() + ".[E][X]" + this.getTitle() + "(at: " + this.date + ")");
        }

    }


}
