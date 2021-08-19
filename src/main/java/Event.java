public class Event extends Task{
    private String date;

    public Event(String str) {
        super(str.split(" /", 2)[0]);
        String[] command = str.split(" /", 2);
        if (command.length == 1) {
            throw new IllegalArgumentException("No commands specified for task 'deadline'!");
        }
        String[] commandAndDate = command[1].split(" ", 2);
        if(!commandAndDate[0].equals("at")) {
            throw new IllegalArgumentException("Unknown command provided to Event! did you use '/at'?");
        } else if (commandAndDate.length == 1) {
            throw new IllegalArgumentException("No date specified!");
        }
        this.date = commandAndDate[1];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(at: " + date + ")";
    }
}
