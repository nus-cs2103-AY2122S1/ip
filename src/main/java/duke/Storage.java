package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

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
            if (task instanceof ToDo) {
                taskType = "T ";
                textToAdd += taskType + "| " + status + "| " + taskDesc + "\n";
            } else {
                if (task instanceof Deadline) {
                    taskType = "D ";
                    taskTime = ((Deadline) task).getBy();
                } else if (task instanceof Event) {
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
