package BobCat.executor;

import BobCat.executor.parser.QueryParser;

import BobCat.model.TaskList;
import BobCat.model.task.Deadline;
import BobCat.model.task.Event;
import BobCat.model.task.Task;
import BobCat.model.task.ToDo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExecutionUnit {
    private final QueryParser parser = new QueryParser();
    private final Storage storeExecutor = new Storage();
    private TaskList taskList = new TaskList();
    private final String storagePath;

    public ExecutionUnit(String storagePath) {
        this.storagePath = storagePath;
    }
    public void initStorage() throws IOException {
        storeExecutor.initStorage(storagePath, taskList);
    }

    public void clearStorage() {
        taskList = new TaskList();
    }

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
        case "find":
                List<String> initReply = new ArrayList<String>();
                initReply.add("Here are the matching tasks in your list:");
                Task[] foundTasks = taskList.findByName(queryArr[1]);
                initReply.addAll(Stream.iterate(1, x -> x + 1)
                        .limit(foundTasks.length)
                        .map(num -> num.toString() + "." + foundTasks[num - 1])
                        .collect(Collectors.toList()));
                return initReply.toArray(new String[0]);
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
