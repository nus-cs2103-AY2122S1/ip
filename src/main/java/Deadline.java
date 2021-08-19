public class Deadline extends Task{
    private String date;

    public Deadline(String str) {
        super(str.split(" /", 2)[0]);
        String[] command = str.split(" /", 2);
        if (command.length == 1) {
            throw new IllegalArgumentException("No commands specified for task 'deadline'!");
        }
        String[] commandAndDate = command[1].split(" ", 2);
        if(!commandAndDate[0].equals("by")) {
            throw new IllegalArgumentException("Unknown command provided to Deadline! did you use '/by'?");
        } else if (commandAndDate.length == 1) {
            throw new IllegalArgumentException("No date specified!");
        }
        this.date = commandAndDate[1];
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + date + ")";
    }
}
