import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task{
    private Date date;
    protected static final String DEADLINE_LABEL = "[D]";

    public Deadline(String str) throws DukeDateParseException {
        super(str.split(" /", 2)[0]);
        String[] command = str.split(" /", 2);
        if (command.length == 1) {
            throw new DukeArgumentException("No commands specified for task 'deadline'!");
        }
        String[] commandAndDate = command[1].split(" ", 2);
        if(!commandAndDate[0].equals("by")) {
            throw new DukeArgumentException("Unknown command provided to Deadline! did you use '/by'?");
        } else if (commandAndDate.length == 1) {
            throw new DukeArgumentException("No date specified!");
        }

        try {
            this.date = new SimpleDateFormat("dd/mm/yyyy").parse(commandAndDate[1]);
        } catch (ParseException e) {
            throw new DukeDateParseException(e);
        }
    }

    @Override
    protected String getTaskType() {
        return DEADLINE_LABEL;
    }

    @Override
    protected String getDate() {
        return this.date.toString();
    }

    @Override
    public String toString() {
        return "[" + DEADLINE_LABEL + "]" + super.toString() + " (by: " + date + ")";
    }
}
