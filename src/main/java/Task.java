import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Constructor of the Task class
     *
     * @param description description of this task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    /**
     * Marks this task as done
     */
    protected void markAsDone() {
        this.isDone = true;
    }
    
    protected boolean isDone() {
        return this.isDone;
    }

    protected String getDescription() {
        return this.description;
    }
    
    /**
     * Returns a string representation of this task
     *
     * @return string representation of this task
     */
    @Override
    public String toString() {
        String taskType = "[" + this.getTaskType() + "]";
        String isDone = "[" + (this.isDone ? "X" : " ") + "]";
        return taskType + " " + isDone + " " + this.description;
    }
    
    protected void appendToFile() throws IOException {
        FileWriter fw = new FileWriter(Duke.FILE_PATH, true);
        fw.append(this.toSavableFormat()).append("\n");
        fw.close();
    }

    /**
     * Solution adapted from https://stackoverflow.com/a/37624091 with modifications.
     * 
     * @param index the line number to overwrite
     */
    protected void updateToFile(int index) throws IOException {
        Path path = Paths.get(Duke.FILE_PATH);
        List<String> fileContent = new ArrayList<>(Files.readAllLines(path));
        fileContent.set(index, this.toSavableFormat());
        Files.write(path, fileContent);
    }
    
    protected abstract String toSavableFormat();
    protected abstract String getTaskType();
}
