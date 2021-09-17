package bobcat.executor.command.basic;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import bobcat.model.TaskList;
import bobcat.model.task.Task;

public class List extends BasicCommand {
    /**
     * Lists all tasks in the given <code>TaskList</code>
     * @param taskList <code>TaskList</code> to be added to
     * @param args NOT USED. Empty String array. Not used for this method
     * @return Array of Strings to display
     */
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
