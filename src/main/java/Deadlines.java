public class Deadlines extends Task {
    private String date;

    public Deadlines(String input) {
        super(input.split(" /by ", 2)[0].substring(9));
        this.date = input.split(" /by ", 2)[1];
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.date);
    }
}
