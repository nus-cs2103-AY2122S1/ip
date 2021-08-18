class Deadline extends Task {
    private String doneBefore;

    public Deadline(String name, String doneBefore) {
        super(name);
        this.doneBefore = doneBefore;
    }

    public static Deadline parseNewCommand(String newCommand) throws IllegalArgumentException {
        int sepIndex = newCommand.indexOf("/by");
        int cmdLen = newCommand.length();
        if (sepIndex == -1 || cmdLen < 9 || 9 > sepIndex-1 || cmdLen < sepIndex+4) {
            throw new IllegalArgumentException("Invalid command for a new deadline.");
        }

        String newName = newCommand.substring(9, sepIndex-1);
        String newDate = newCommand.substring(sepIndex+4);

        return new Deadline(newName, newDate);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.doneBefore);
    }
}