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

    public static void isLegitInput(String input) throws NotEnoughInfoException {
        if (input.split("/at").length != 2) {
            throw new NotEnoughInfoException("There should be exactly one '/at' in statement with start and end date/time after");
        } else if (input.split("/at")[0].split("event")[1].strip() == ""){
            throw new NotEnoughInfoException("Could not find name of Task");
        }
    }

    public static String getNameInput(String input) {
        return input.split("/at")[0].split("event")[1].strip();
    }

    public static String getDeadlineInput(String input) {
        return input.split("/at")[1].strip();
    }
}
