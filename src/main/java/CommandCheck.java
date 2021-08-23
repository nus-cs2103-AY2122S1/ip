import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;


public class CommandCheck extends Command {
    public static final String KEYWORD = "check";
    private ArrayList<String> arguments;


    public CommandCheck(ArrayList<String> arguments) {
        this.arguments = arguments;
    }

    @Override
    public boolean isArgumentValid() {
        try {
            if (arguments.size() == 1) {
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(arguments.get(0), dateFormatter);
                return true;
            } else {
                return false;
            }
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public void execute(TaskList tl, Storage st, Ui ui) {
        if (isArgumentValid()) {
            tl.printAllTasksOnDate(arguments.get(0));
        } else {
            throw new DukeException("Invalid argument for command: check");
        }
    }

}
