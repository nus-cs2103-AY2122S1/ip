package bobcat.executor.command.basic;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import bobcat.model.TaskList;
import bobcat.model.task.Task;

public class Find extends BasicCommand {
    public static String[] execute(TaskList taskList, String... args) {
        java.util.List<String> initReply = new ArrayList<String>();
        initReply.add("Here are the matching tasks in your list:");
        Task[] foundTasks = taskList.findByName(args[1]);
        initReply.addAll(Stream.iterate(1, x -> x + 1)
                .limit(foundTasks.length)
                .map(num -> num.toString() + "." + foundTasks[num - 1])
                .collect(Collectors.toList()));
        return initReply.toArray(new String[0]);
    }
}


