import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }
    private void updatePersistedDataToMarkAsDone(int taskNum) {
        Path persistedDataPath = Paths.get(Duke.persistedData);
        try {
            List<String> allLines = Files.readAllLines(persistedDataPath);
            allLines.set(taskNum, this.persistedDataStringFormat());
            Files.write(persistedDataPath, allLines);
        } catch (IOException e) {
            System.out.println("A problem occurred while reading from the text file.");
            e.printStackTrace();
        }

    }
    public void markAsDone(int taskNum) {
        this.isDone = true;
        updatePersistedDataToMarkAsDone(taskNum);
        System.out.println("Good job! I've marked this task as done:");
        System.out.println(this.toString());
    }

    public abstract String persistedDataStringFormat();

    @Override
    public String toString() {
        String checkbox = this.isDone ? "[X] " : "[ ] ";
        return checkbox + this.description;
    }
}