import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves TaskList to file.
     */
    public void save(TaskList taskList) throws IOException {
        StringBuilder saveData = new StringBuilder();
        FileWriter fw;
        for (int i = 1; i < taskList.getSize() + 1; i++) {
            saveData.append(taskList.get(i).formatForFile());
        }

        File file = new File(filePath);
        file.createNewFile();
        fw = new FileWriter(file);
        fw.write(saveData.toString());
        fw.close();

    }

    public TaskList load() throws FileNotFoundException {
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        TaskList taskList = new TaskList();
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split("\\" + Task.SAVE_DATA_MARKER);
            String taskType = line[0];
            boolean isDone = Integer.parseInt(line[1]) != 0;
            String taskName = line[2];
            switch (taskType) {
                case "T":
                    taskList.add(new TodoTask(taskName, isDone));
                    break;
                case "E":
                    taskList.add(new EventTask(taskName, isDone, line[3]));
                    break;
                case "D":
                    taskList.add(new DeadlineTask(taskName, isDone, line[3]));
                    break;
                default:
                    break;
            }
        }
        return taskList;
    }
}
