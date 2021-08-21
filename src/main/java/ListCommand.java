import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ListCommand implements Command {
    /**
     * Main codes to run for the chat.
     *
     * @param taskList TaskList to execute the command.
     * @param ui       To interact with the user.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        List<Task> allTask = taskList.getTaskList();
        String[] task = IntStream.range(0, allTask.size())
                .mapToObj(x -> (x + 1) + ". " + allTask.get(x).toString())
                .collect(Collectors.toList())
                .toArray(new String[0]);
        ui.listTask(task);
    }
}
