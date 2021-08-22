import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

public class Todo extends Task {
    private static final char TASK_LETTER = 'T';

    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[%c]%s", Todo.TASK_LETTER, super.toString());
    }

    @Override
    public boolean save(File file) {
        String taskInfo = Todo.TASK_LETTER + " | " + this.getStatusIcon() + " | " + this.description + "\n";
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.write(taskInfo);
            fw.flush();
            fw.close();
            return true;
        } catch (IOException e) {
            return false;
        }

    }
}
