import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String saveFileLocation;

    public Storage(String saveFileLocation) {
        this.saveFileLocation = saveFileLocation;
    }

    public TaskList loadTaskList() throws DukeStorageException {
        try {
            FileInputStream fileInputStream = new FileInputStream(saveFileLocation);
            Scanner scanner = new Scanner(fileInputStream);
            List<DukeTask> tasks = new ArrayList<>();
            while (scanner.hasNext()) {
                tasks.add(DukeTask.fromSerializedString(scanner.nextLine()));
            }
            scanner.close();
            fileInputStream.close();
            return new TaskList(tasks);
        } catch (IOException e) {
            return new TaskList();
        } catch (TaskParseException e) {
            throw new DukeStorageException(String.format("Error when reading %s; file ignored", saveFileLocation));
        }
    }

    public void saveTaskList(TaskList taskList) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(saveFileLocation);
            for (DukeTask task: taskList.getTasks()) {
                fileOutputStream.write(task.toSerializedString().getBytes());
                fileOutputStream.write("\n".getBytes());
            }
            fileOutputStream.close();
        } catch (IOException e) {
            // File not found
            e.printStackTrace();
        }
    }

}
