import java.util.List;
import java.util.stream.Collectors;

public class ListTasksByDate extends ListTasks {
    private LocalDateTimeOrString dateTimeOrString;

    public ListTasksByDate(List<Task> taskList, String input) throws MissingArgumentException {
        super(taskList);
        String[] inputs = input.split("/date ", 2);
        InputChecker.checkMissingArguments(inputs, "Please specify a date & time.\n");
        this.dateTimeOrString = new LocalDateTimeOrString(inputs[1]);
    }

    @Override
    void performOperation() {
        List<Task> matchingTasks = getTasks().stream().filter(x -> x.isAtTime(dateTimeOrString))
                .collect(Collectors.toList());
        String description = listTasks(matchingTasks);
        setDescription(description);
    }
}
