package commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import bot.Bot;
import tasks.Task;

/**
 * Command for finding task by search string
 */
public class FindCommand extends Command {

    @Override
    public String[] run(Bot bot, String[] args) {
        List<Task> taskList = bot.getTaskList().get();
        List<Task> matchingTasks = taskList.stream()
                .filter(t -> t.getTaskText().contains(args[0]))
                .collect(Collectors.toList());
        List<String> taskStrings = new ArrayList<String>(Arrays.asList(ListCommand.commandsToStrings(matchingTasks)));
        taskStrings.add(0, matchingTasks.size() > 0 ? "heer r ze metching taskz:" : "no metches :(");
        return taskStrings.toArray(new String[0]);
    }

}
