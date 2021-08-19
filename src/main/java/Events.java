public class Events extends Task {
    private String date;

    public Events(String input) {
        super(input.split(" /at ", 2)[0].substring(6));
        this.date = input.split(" /at ", 2)[1];
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), this.date);
    }
}
