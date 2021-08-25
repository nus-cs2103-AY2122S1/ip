import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddDeadlineCommand extends Command {

    // Regex pattern for finding deadline commands
    private static final Pattern DEADLINE_PATTERN = Pattern.compile("^deadline (.*) /by (\\d{4}-\\d{2}-\\d{2})$");

    AddDeadlineCommand(String input) {
        super(input);
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui) {
        Matcher matcher = DEADLINE_PATTERN.matcher(input);
        if (!matcher.find()) {
            throw new DukeException("Give me a deadline like this: deadline <task> /by YYYY-MM-DD");
        }
        String description = matcher.group(1);
        String endDateTime = matcher.group(2);

        Deadline deadline = new Deadline(description, false, parseDate(endDateTime));

        // Add to store
        tasks.add(deadline);

        // Inform user
        ui.notifyAdd(deadline, tasks.size());

        return false;
    }
}
