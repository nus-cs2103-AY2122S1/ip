class Event extends Task{
    private String eventDate;

    public Event(String name, String eventDate) {
        super(name);
        this.eventDate = eventDate;
    }

    public static Event parseNewCommand(String newCommand) {
        int sepIndex = newCommand.indexOf("/at");
        String newName = newCommand.substring(6, sepIndex-1);
        String newDate = newCommand.substring(sepIndex+4);

        return new Event(newName, newDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (by: %s)", this.eventDate);
    }
}