package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    private final Ui ui = new Ui();
    private Path folderPath;

    private final String DATA_FILEPATH = "data/duketest.txt"; //fixed file path

    public Storage(String folderPathString){
        this.folderPath = Paths.get(folderPathString);
    }

    public File load() throws IOException {
        if (!Files.exists(folderPath)) {
            folderPath = Files.createDirectory(folderPath);
        }
        File file = folderPath.resolve("duketest.txt").toFile();
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    public void save(StorageList storageList) throws IOException {
        FileWriter fw = new FileWriter(DATA_FILEPATH);
        String textToAdd = "";

        for (int i = 0; i < storageList.size(); i++) {
            Task task = storageList.get(i);
            String taskType = "";
            String status = task.isDone() ? "1 " : "0 ";
            String taskDesc = task.getDescription();
            String taskTime = "";
            if (task instanceof ToDo) {
                taskType = "T ";
                textToAdd += taskType + "| " + status + "| " + taskDesc + "\n";
            } else {
                if (task instanceof Deadline) {
                    taskType = "D ";
                    taskTime = ((Deadline) task).getBy();
                } else {

                    taskType = "E ";
                    taskTime = ((Event) task).getAt();
                }
                textToAdd += taskType + "| " + status + "| " + taskDesc + " | " + taskTime + "\n";
            }

        }

        fw.write(textToAdd);
        fw.close();
    }
}
