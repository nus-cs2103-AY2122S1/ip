package bobcat.executor.command.basic;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import bobcat.model.TaskList;
import bobcat.model.task.Task;

public class Find extends BasicCommand {
    /**
     * Finds a <code>Task</code> to the provided <code>TaskList</code>, with <code>args</code> being the description
     * field
     * @param taskList <code>TaskList</code> to be modified
     * @param args description of <code>Task</code> to be found. Can be partially written
     * @return Array of Strings to display
     */
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


