package executor;

import executor.parser.QueryParser;

import model.TaskList;
import model.task.Deadline;
import model.task.Event;
import model.task.Task;
import model.task.ToDo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Represents the "brain" of the application. Implements the "Controller" aspect of the MVC pattern. An
 * <code>ExecutionUnit</code> object dictates <i>how</i> should data in storage should be manipulated according to the
 * given command.
 * <p>
 *     Should the need arise e.g. too many commands, this is the first place to look at for a refactoring.
 *     Easiest way is to abstract out the switch statement and make a Command class. Right now it's simple enough
 *     to not require an abstraction.
 * </p>
 */
public class ExecutionUnit {
    private final QueryParser parser = new QueryParser();
    private final Storage storeExecutor = new Storage();
    private TaskList taskList = new TaskList();
    private final String storagePath;

    /**
     * Public constructor to initialise filepath to saved data.
     * @param storagePath Relative path to storage data. Must end in .txt
     */
    public ExecutionUnit(String storagePath) {
        this.storagePath = storagePath;
    }

    /**
     * Initialises TaskList according to given storagePath.
     * @throws IOException May throw IOException if given storagePath during initialisation is inappropriate
     */
    public void initStorage() throws IOException {
        storeExecutor.initStorage(storagePath, taskList);
    }

    /**
     * Clears <code>TaskList</code>. This will irreversibly delete content of <code>TaskList</code>, as well as storage file.
     */
    public void clearStorage() {
        taskList = new TaskList();
    }

    /**
     * Executes the given query. Query will be parsed and executed. May throw a <code>BobCatException</code>
     * @param query The query to run on TaskList
     * @throws IOException May throw IOException if given storagePath during initialisation is inappropriate
     */
    public String[] executeCommand(String query) throws IOException {
        String[] queryArr;
        queryArr = parser.parse(query);

        String command = queryArr[0];
        switch (command) {
            case "list":
                List<String> initialReply = new ArrayList<String>();
                initialReply.add("Here are the tasks in your list:");
                Task[] toShow = taskList.getAllTasks();
                initialReply.addAll(Stream.iterate(1, x -> x + 1)
                        .limit(toShow.length)
                        .map(num -> num.toString() + "." + toShow[num - 1])
                        .collect(Collectors.toList()));
                return initialReply.toArray(new String[0]);
            case "bye":
                return new String[]{"Bye! Hope to see you again soon!"};
            case "done":
                Task markedTask = taskList.markDone(Integer.parseInt(queryArr[1]));
                storeExecutor.saveStorage(storagePath, taskList);
                return new String[]{"Nice! I've marked this task as done:", "  " + markedTask.toString()};
            case "delete":
                Task deletedTask = taskList.deleteTaskByIdx(Integer.parseInt(queryArr[1]));
                storeExecutor.saveStorage(storagePath, taskList);
                return new String[]{"Noted. I've removed this task:",
                        "  " + deletedTask.toString(),
                        "Now you have " + taskList.numTasks() + " tasks in the list"};
            default:
                Task toAdd;
                if (command.equals("todo")) {
                    toAdd = new ToDo(queryArr[1]);
                } else if (command.equals("deadline")) {
                    toAdd = new Deadline(queryArr[1], queryArr[2]);
                } else {
                    toAdd = new Event(queryArr[1], queryArr[2]);
                }
                Task addedTask = taskList.push(toAdd);
                storeExecutor.saveStorage(storagePath, taskList);
                return new String[]{"Got it. I've added this task:",
                        "  " + addedTask.toString(),
                        "Now you have " + taskList.numTasks() + " tasks in the list"};
        }
    }
}
