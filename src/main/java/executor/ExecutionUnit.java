package executor;

import executor.parser.StorageParser;
import model.Storage;
import model.task.Deadline;
import model.task.Event;
import model.task.Task;
import executor.parser.QueryParser;
import model.task.ToDo;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExecutionUnit {
    private final QueryParser parser = new QueryParser();
    private Storage storage = new Storage();

    public void initStorage(String storagePath) throws IOException {
        StorageParser storeParser = new StorageParser();
        Path path = Paths.get(storagePath);
        List<String> read = Files.readAllLines(path);
        for (String storeCmd : read) {
            Task t = storeParser.parse(storeCmd);
            storage.push(t);
        }
    }

    // TODO: Find a better way to do this..
    public void saveStorage(String storagePath) throws IOException {
        Path path = Paths.get(storagePath);
        Files.createDirectories(path.getParent());
        Files.deleteIfExists(path);
        Files.createFile(path);

        Task[] all = storage.getAllTasks();
        BufferedWriter bw = Files.newBufferedWriter(path, StandardOpenOption.APPEND);

        for (Task t : all) {
            bw.write(t.toString() + "\n");
        }
        bw.close();
    }

    public void initStorage() throws IOException {
        initStorage("memory/storage.txt");
    }

    public void saveStorage() throws IOException {
        saveStorage("memory/storage.txt");
    }

    public String[] executeCommand(String query) throws IOException {
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
                this.saveStorage();
                return new String[]{"Nice! I've marked this task as done:", "  " + markedTask.toString()};
            case "delete":
                Task deletedTask = storage.deleteTaskByIdx(Integer.parseInt(queryArr[1]));
                this.saveStorage();
                return new String[]{"Noted. I've removed this task:",
                        "  " + deletedTask.toString(),
                        "Now you have " + storage.numTasks() + " tasks in the list"};
            default:
                Task toAdd;
                if (command.equals("todo")) {
                    toAdd = new ToDo(queryArr[1]);
                } else if (command.equals("deadline")) {
                    toAdd = new Deadline(queryArr[1], queryArr[2]);
                } else {
                    toAdd = new Event(queryArr[1], queryArr[2]);
                }
                Task addedTask = storage.push(toAdd);
                this.saveStorage();
                return new String[]{"Got it. I've added this task:",
                        "  " + addedTask.toString(),
                        "Now you have " + storage.numTasks() + " tasks in the list"};
        }
    }

    public void storageClear() {
        storage = new Storage();
    }
}
