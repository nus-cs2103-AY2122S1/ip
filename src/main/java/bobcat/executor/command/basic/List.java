package bobcat.executor.command.basic;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import bobcat.model.TaskList;
import bobcat.model.task.Task;

public class List extends BasicCommand {
    public static String[] execute(TaskList taskList, String... args) {
        java.util.List<String> initialReply = new ArrayList<String>();
        initialReply.add("Here are the tasks in your list:");
        Task[] toShow = taskList.getAllTasks();
        initialReply.addAll(Stream.iterate(1, x -> x + 1)
                .limit(toShow.length)
                .map(num -> num.toString() + "." + toShow[num - 1])
                .collect(Collectors.toList()));
        return initialReply.toArray(new String[0]);
    }
}
