public class Deadlines extends Task {
    private static String label = "[D]";
    private String dl;
    public Deadlines(String s, String dl) {
        super(s.trim());
        this.dl = "(by: " + dl.trim() + ")";
    }

    @Override
    public String toString() {
        return this.label + super.toString() + " " + this.dl;
    }
}
