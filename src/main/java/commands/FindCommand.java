package commands;

import bot.Bot;
import bot.Ui;
import tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Command for finding task by search string
 */
public class FindCommand extends Command {

    @Override
    public void run(Bot bot, String[] args) {
        List<Task> taskList = bot.taskList.get();
        List<Task> matchingTasks = taskList.stream()
                .filter(t -> t.getTaskText().contains(args[0]))
                .collect(Collectors.toList());
        List<String> taskStrings =  new ArrayList<String>(Arrays.asList(ListCommand.commandsToStrings(matchingTasks)));
        taskStrings.add(0, "Here are the matching tasks in your list:");
        Ui.print(taskStrings.toArray(new String[0]));
    }

}
