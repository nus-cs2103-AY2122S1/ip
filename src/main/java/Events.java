public class Events extends Task {

    private final String startend;

    Events(String name, boolean done, String startend){
        super(name, done);
        this.startend = startend;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (at: %s)", (getDone())? "X" : " ", getName(), this.startend);
    }

    @Override
    Task markDone() {
        return new Events(getName(), true, this.startend);
    }

    public static String getNameInput(String input) {
        return input.split("/at")[0].split("event")[1].strip();
    }

    public static String getDeadlineInput(String input) {
        return input.split("/at")[1].strip();
    }
}
