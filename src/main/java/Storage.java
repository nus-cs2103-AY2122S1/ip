import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> loadStorage() {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                createEmptyFile(file);
            } else {
                return getDataFromStorage(file);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new ArrayList<>();
    }

    public List<Task> getDataFromStorage(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        List<Task> taskList = new ArrayList<>();
        try {
            while (sc.hasNextLine()) {
                String currTask = sc.nextLine();
                System.out.println(currTask);
                String[] taskDetails = currTask.split(" \\| ");
                Task task;
                String type = taskDetails[0];
                String status = taskDetails[1];
                String description = taskDetails[2];
                switch (type) {
                case "T": {
                    task = new ToDo(description);
                    break;
                }
                case "D": {
                    task = new Deadline(description, taskDetails[3]);
                    break;
                }
                case "E": {
                    task = new Event(description, taskDetails[3]);
                    break;
                }
                default: {
                    throw new FormatNotUnderstoodException(currTask);
                }
                }
                if (status.equals("1")) task.setDone();
                taskList.add(task);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return taskList;
    }

    public void createEmptyFile(File file) throws IOException {
        file.getParentFile().mkdirs();
        file.createNewFile();
    }

    public void saveToStorage(List<Task> tasks){
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task: tasks) fw.write(task.toString() + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}