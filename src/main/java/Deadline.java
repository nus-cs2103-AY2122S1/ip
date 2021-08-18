class Deadline extends Task {
    private String doneBefore;

    public Deadline(String name, String doneBefore) {
        super(name);
        this.doneBefore = doneBefore;
    }

    public static Deadline parseNewCommand(String newCommand) {
        int sepIndex = newCommand.indexOf("/by");
        String newName = newCommand.substring(9, sepIndex-1);
        String newDate = newCommand.substring(sepIndex+4);

        return new Deadline(newName, newDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.doneBefore);
    }
}