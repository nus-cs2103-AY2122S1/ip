package executor;

import model.Storage;
import model.task.Task;
import executor.parser.QueryParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExecutionUnit {
    private final QueryParser parser = new QueryParser();
    private Storage storage = new Storage();

    public void initStorage(String storagePath) throws IOException {
        Path path = Paths.get(storagePath);
        List<String> read = Files.readAllLines(path);
        for (String cmd : read) {
            this.executeCommand(cmd);
        }
    }

    public void initStorage() throws IOException {
        initStorage("src/main/resources/storage.txt");
    }

    public String[] executeCommand(String query) {
        String[] queryArr;
        queryArr = parser.parse(query);

        String command = queryArr[0];
        switch (command) {
            case "list":
                List<String> initialReply = new ArrayList<String>();
                initialReply.add("Here are the tasks in your list:");
                Task[] toShow = storage.getAllTasks();
                initialReply.addAll(Stream.<Integer>iterate(1, x -> x + 1)
                        .limit(toShow.length)
                        .map(num -> num.toString() + "." + toShow[num - 1])
                        .collect(Collectors.toList()));
                return initialReply.toArray(new String[0]);
            case "bye":
                return new String[]{"Bye! Hope to see you again soon!"};
            case "done":
                Task markedTask = storage.markDone(Integer.parseInt(queryArr[1]));
                return new String[]{"Nice! I've marked this task as done:", "  " + markedTask.toString()};
            case "delete":
                Task deletedTask = storage.deleteTaskByIdx(Integer.parseInt(queryArr[1]));
                return new String[]{"Noted. I've removed this task:",
                        "  " + deletedTask.toString(),
                        "Now you have " + storage.numTasks() + " tasks in the list"};
            default:
                Task addedTask = storage.push(queryArr);
                return new String[]{"Got it. I've added this task:",
                        "  " + addedTask.toString(),
                        "Now you have " + storage.numTasks() + " tasks in the list"};
        }
    }

    public void storageClear() {
        storage = new Storage();
    }
}
