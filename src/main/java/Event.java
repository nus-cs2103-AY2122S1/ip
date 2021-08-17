public class Event extends Task {
    private String date;

    public Event(String description, String date) {
        super(description, Type.EVENT);
        this.date = date;
    }

    @Override
    public String toString() {
        return getTypeBox() + getCheckBox() + description + "(at: "
                + date + ")";
    }
}
