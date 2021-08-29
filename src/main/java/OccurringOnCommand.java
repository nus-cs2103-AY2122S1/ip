import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class OccurringOnCommand extends Command {
    private LocalDate date;

    public OccurringOnCommand(String date) throws DukeException {
       try {
           this.date = LocalDate.parse(date);
       } catch (DateTimeParseException e) {
           throw new DukeException("OOPS!! Please enter date in format yyyy-mm-dd");
       }
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.findTasksOnDate(date);
    }

    public boolean isExit() {
        return false;
    }
}
