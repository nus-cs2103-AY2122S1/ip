import java.io.FileWriter;
import java.io.IOException;

public class Event extends Task{
    private String taskName;
    private String at;
    private String type = "E";

    public Event(String taskName) {
        String[] TaskBySplit = taskName.split("/", 2);
        String[] removeAt = TaskBySplit[1].split(" ", 2);
        this.taskName = TaskBySplit[0].trim();
        this.at = removeAt[1].trim();
    }

    public String showTask() {
        return this.taskName + "(at: " + this.at + ")";
    }

    public String showTaskOnly() { return this.taskName; }

    public String showWhen() { return this.at; }

    public String showType() {
        return type;
    }

    public void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }
}
