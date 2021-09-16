package duke.misc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Storage class to retrieve and store tasks in Duke.
 */
public class Storage {
    private final String currentDirectory = System.getProperty("user.dir");
    private final Path filePath = Paths.get(currentDirectory, "Data", "duke.txt");
    private final boolean filePathExists = Files.exists(filePath);
    private final boolean fileDirExists = Files.exists(filePath.getParent());

    /**
     * Constructor for Storage class.
     */
    public Storage() {
    }

    /**
     * Reads data from duke.txt file.
     *
     * @return ArrayList of saved tasks.
     * @throws IOException In case the file directory is invalid..
     */
    public ArrayList<Task> readData() throws IOException {
        ArrayList<Task> al = new ArrayList<>();
        if (!filePathExists) {
            return al;
        }
        File file = new File(filePath.toString());
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String data = sc.nextLine();
            String[] args = data.split(" // ");
            Boolean isTaskDone = Integer.parseInt(args[1]) != 0;
            switch(args[0]) {
            case "Todo":
                assert args.length == 3;
                al.add(new Todo(args[2], isTaskDone));
                break;
            case "Event":
                assert args.length == 5;
                al.add(new Event(args[2], args[3], args[4], isTaskDone));
                break;
            case "Deadline":
                assert args.length == 5;
                al.add(new Deadline(args[2], args[3], args[4], isTaskDone));
                break;
            default:
                assert false : "Wrote data wrongly";
                break;
            }
        }
        return al;
    }

    /**
     * Writes data into duke.txt file.
     *
     * @param al The ArrayList of tasks to write from.
     * @throws IOException In case the file directory is invalid.
     */
    public void writeData(ArrayList<Task> al) throws IOException {
        if (!fileDirExists) {
            Path parentPath = filePath.getParent();
            File parentDir = parentPath.toFile();
            parentDir.mkdir();
        }
        FileWriter fw = new FileWriter(filePath.toFile());
        String data = al.stream()
                .map(Task::getData)
                .collect(Collectors.joining("\n"));
        fw.write(data);
        fw.close();
    }
}
