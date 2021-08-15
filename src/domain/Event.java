package domain;

public class Event extends Task {
    private String dateRange;

    public Event(String name, String dateRange) {
        super(name);
        typeString = "E";
        this.dateRange = dateRange;
    }

    @Override
    public String toString() {
        String base = super.toString();
        String result = String.format("%s (at: %s)", base, dateRange);
        return result;
    }
}
