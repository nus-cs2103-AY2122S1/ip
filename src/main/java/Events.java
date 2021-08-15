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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Events) {
            Events e = (Events) obj;
            return this.name.equals(e.name) && e.date.equals(this.date);
        }
        return false;
    }
}
