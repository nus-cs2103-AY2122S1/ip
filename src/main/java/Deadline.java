import java.io.FileWriter;
import java.io.IOException;

public class Deadline extends Task {
    private String taskName;
    private String by;
    private String type = "D";

    public Deadline(String taskName) {
        String[] TaskBySplit = taskName.split("/", 2);
        String[] removeBy = TaskBySplit[1].split(" ", 2);
        this.taskName = TaskBySplit[0].trim();
        this.by = removeBy[1].trim();
    }

    public String showTask() {
        return this.taskName + "(by: " + this.by + ")";
    }

    public String showTaskOnly() { return this.taskName; }

    public String showWhen() { return this.by; }

    public String showType() {
        return this.type;
    }

    public void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true);
        fw.write(textToAppend);
        fw.close();
    }
}
