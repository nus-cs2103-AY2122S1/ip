import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
public class ListCommand implements Command {

  @Override
  public void run(Bot bot, String[] args) {
    List<Task> taskList = bot.getTaskList();
    List<String> taskDescriptions = IntStream.range(0, taskList.size())
                          .mapToObj(i -> {
                            Task t = taskList.get(i);
                            return String.format("%d. %s", i+1, t.toString());
                          })
                          .collect(Collectors.toList());
    bot.printOutput(taskDescriptions.toArray(new String[0]));
  }
  
}
