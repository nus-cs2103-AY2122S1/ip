public class Event extends Task {
    protected String at;

    public Event(String name, String at, Boolean isDone) {
        super(name,'E', isDone);
        this.at = at;
    }
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }

    public static Task parseCommand(String str) throws TaskException {
        String[] detailE = str.split(" /at ", 2);
        if (detailE.length == 1) {
            throw new TaskException("When is the event?");
        }
        Event newE = new Event(detailE[0], detailE[1], false);
        return newE;
    }

    @Override
    public String getSavedAs() {
        return (super.getSavedAs() + "|" + this.at);
    }
}
