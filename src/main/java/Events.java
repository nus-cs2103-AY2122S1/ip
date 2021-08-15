public class Events extends Task {
    private static String label = "[E]";
    private String date;
    public Events(String name, String date) {
        super(name.trim());
        this.date = "(at: " + date.trim() + ")";
    }

    @Override
    public String toString() {
        return this.label + super.toString() + " " + this.date;
    }
}
