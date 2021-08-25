package commands;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import bot.Bot;
import bot.Ui;
import tasks.Task;

/**
 * Command for listing Bot's taskList
 */
public class ListCommand extends Command {

    @Override
    public void run(Bot bot, String[] args) {
        List<Task> taskList = bot.taskList.get();
        List<String> taskDescriptions = IntStream.range(0, taskList.size())
                .mapToObj(i -> {
                    Task t = taskList.get(i);
                    return String.format("%d. %s", i + 1, t.toString());
                })
                .collect(Collectors.toList());
        Ui.print(taskDescriptions.toArray(new String[0]));
    }

}
