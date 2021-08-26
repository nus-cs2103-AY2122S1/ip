public class Deadline extends Task{
    protected String by;
    public Deadline(String name, String by, Boolean isDone) {
        super(name, 'D', isDone);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public static Task parseCommand(String str) throws TaskException {
        String[] detailD = str.split(" /by ", 2);
        if (detailD.length == 1) {
            throw new TaskException("When is the deadline?");
        }
        Deadline newD = new Deadline(detailD[0], detailD[1],false);
        return newD;
    }
    @Override
    public String getSavedAs() {
        return (super.getSavedAs() + "|" + this.by);
    }
}
