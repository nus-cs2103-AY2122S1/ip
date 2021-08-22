import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Event extends Task {
    private String at;
    private static final char TASK_LETTER = 'E';

    public Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("[%c]%s (at: %s)", Event.TASK_LETTER, super.toString(), this.at);
    }

    @Override
    public boolean save(File file) {
        String taskInfo = Event.TASK_LETTER + " | " + this.getStatusIcon() + " | " + this.description + " | "
                + this.at + "\n";
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
