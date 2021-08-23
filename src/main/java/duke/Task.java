package duke;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;

public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsDoneAndUpdate() throws IOException{
        this.isDone = true;
        for (int i = 0; i < TaskList.getTaskList().size(); i++) {
            if (i == 0) {
                //writeToFile("data/jarvis.txt", i + ".");
                writeToFile("data/jarvis.txt", (i + 1) + "." +
                        TaskList.getTaskList().get(i).toString()
                        + System.lineSeparator());
            } else {
                //appendToFile("data/jarvis.txt", i + "." );
                appendToFile("data/jarvis.txt", (i + 1) + "." +
                        TaskList.getTaskList().get(i).toString()
                        + System.lineSeparator());
            }
        }
    }

    public String getDescription() {
        return this.description;
    }
    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }

    public String toPrintToFile() {
        return this.getStatusIcon() + this.description;
    }

    private static void writeToFile(String filePath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(textToAdd);
        fw.close();
    }

    private static void appendToFile(String filePath, String textToAppend) throws IOException {
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

}