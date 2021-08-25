public class Event extends Task{
    private String date;
    protected static final String EVENT_LABEL = "E";

    public Event(String str) {
        super(str.split(" /", 2)[0]);
        String[] command = str.split(" /", 2);
        if (command.length == 1) {
            throw new DukeArgumentException("No commands specified for task 'deadline'!");
        }
        String[] commandAndDate = command[1].split(" ", 2);
        if(!commandAndDate[0].equals("at")) {
            throw new DukeArgumentException("Unknown command provided to Event! did you use '/at'?");
        } else if (commandAndDate.length == 1) {
            throw new DukeArgumentException("No date specified!");
        }
        this.date = commandAndDate[1];
    }

    @Override
    protected String getTaskType() {
        return EVENT_LABEL;
    }

    @Override
    protected String getDate() {
        return this.date;
    }

    @Override
    public String toString() {
        return "[" + EVENT_LABEL + "]" + super.toString() + " (at: " + date + ")";
    }
}
