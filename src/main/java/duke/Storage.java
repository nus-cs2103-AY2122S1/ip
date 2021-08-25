package duke;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.Task;
import duke.task.ToDos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Storage {
    private String filepath;
    private Ui ui = new Ui();

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public File load() throws FileNotFoundException {
        File file = new File(filepath);
        if (!file.exists()) {
            throw new FileNotFoundException();
        }
        ui.fileConfirmation();
        return file;
    }

    public void save(StorageList storageList) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        String textToAdd = "";

        for (int i = 0; i < storageList.size(); i++) {
            Task task = storageList.get(i);
            String taskType = "";
            String status = task.isDone() ? "1 " : "0 ";
            String taskDesc = task.getDescription();
            String taskTime = "";
            if (task instanceof ToDos) {
                taskType = "T ";
                textToAdd += taskType + "| " + status + "| " + taskDesc + "\n";
            } else {
                if (task instanceof Deadlines) {
                    taskType = "D ";
                    taskTime = ((Deadlines) task).getBy();
                } else if (task instanceof Events) {
                    taskType = "E ";
                    taskTime = ((Events) task).getAt();
                }
                textToAdd += taskType + "| " + status + "| " + taskDesc + " | " + taskTime + "\n";
            }

        }

        fw.write(textToAdd);
        fw.close();
    }
}
