package duke.task;

import duke.task.Task;

import java.io.FileWriter;
import java.io.IOException;

public class ToDo extends Task {
    private String taskName;
    private String type = "T";

    public ToDo(String taskName) {
        this.taskName = taskName;
    }

    public String showTask() {
        return taskName;
    }

    public String showType() {
        return type;
    }
    public String showWhen() { return ""; }

    public String showTaskOnly() { return ""; }

    public void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }
}
